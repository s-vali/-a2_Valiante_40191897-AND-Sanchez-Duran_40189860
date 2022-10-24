import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Part2_B {

	public static void main(String[] args) {

		/*Declare Variables and Constants*/
		int top, symmetricalTop, stackSize, stackZero;
		int count = 0;
		String line;
		
		/*Declare Stacks*/
		Stack gameStack = null;
		Stack tempStack = null;
		
		/*Declare Streams*/
		Scanner inFile = null;
		PrintWriter outFile = null;
		
		try {
			
			/*Read and Write from Files*/
			inFile = new Scanner(new FileInputStream("in.txt"));
			outFile = new PrintWriter(new FileOutputStream("out.txt"));
			System.out.println(count);
						
			/*Read inFile line by line*/
			while(inFile.hasNextLine()) {
				if(count <= 0) { //First line of input file contains how many games there are
					inFile.nextLine();
				}
				count++;
				line = inFile.nextLine();
				String[] element = line.split("\s");	
				stackSize = Integer.parseInt(element[0]);
				gameStack = new Stack(stackSize);
				tempStack = new Stack(stackSize);
				gameStack.push(0);
				for(int k = stackSize; k > 1; k--) {
					gameStack.push(Integer.parseInt(element[k]));	
				}
		
				/*Game*/
				do {
					System.out.print("gameStack: "); gameStack.print();
					System.out.print("tempStack: "); tempStack.print(); 
					System.out.println();
					
					top = gameStack.top();
					symmetricalTop = top;
					
					if(top <= gameStack.size()-1) {
						if(((gameStack.top() == symmetricalTop) && (top != 1) && (top == gameStack.size()-2))) {
							System.out.print("gameStack (symmetricalTop before): "); gameStack.print();
							top = gameStack.top();
							if(top <= tempStack.size() ) {
								for(int j = 0; j < top; j++) {
									gameStack.push(tempStack.pop()); //changing top
								}
							}
							else {
								outFile.print("0\n");
								System.out.println("reached here! (little else)");
								break;
							}
						}
						else {
							for(int j = 0; j < top; j++) {
								tempStack.push(gameStack.pop()); //changing top
							}
						} 
						
					}
					else if (top >= gameStack.size()-1 && top <= tempStack.size()){
						for(int j = 0; j < top; j++) {
							gameStack.push(tempStack.pop()); //changing top
						}	
					}
					else {						
						outFile.print("0\n");
						System.out.println("reached here! (big else)");
						break;
					}
				} while(gameStack.top() != 0);
				if(gameStack.top() == 0) {
					outFile.print("1\n");
				}
			}	
		}
		catch(FileNotFoundException e) {
			System.err.println("Error: File could not be found! Program will terminate. ");
			System.exit(0);
			
		}
		catch(IOException e) {
			System.err.println("Error: IOException encountered! Program will terminate. ");
			System.exit(0);
		}
		catch(EmptyStackException e) {
			System.exit(0);
		}	
		catch(FullStackException e) {
			System.exit(0);
		}
		finally {
			if(outFile != null) {
				outFile.close();
			}
			if(inFile != null) {
				inFile.close();
			}
		}
	}
}