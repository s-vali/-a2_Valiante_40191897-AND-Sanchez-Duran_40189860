import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Part2_B {
	
	private class stackStrikeEdgeZero {
		
		/*Declare Variables and Constants*/
		static int top, symmetricalTop, stackZero;
		
		/**
		 * The function stackStrikeEdgeZero incorporates two stacks gameStack and tempStack in 
		 * order to solve the Strike Edge Zero game. The output of the game is an integer, if 
		 * the game is solved, the output is 1, if not, the output is 0. The method makes use
		 * of multiple loops, one controlling the game that ends when it is solved, and the 
		 * other popping or pushing the amount of times specified by the current top of the stack. 
		 * The game traverses along the conditions expressed in the method. 
		 * 
		 * @param gameStack
		 * @param tempStack
		 * @return integer
		 * @throws FullStackException
		 * @throws EmptyStackException
		 */
		
		private static int stackStrikeEdgeZero(Stack gameStack, Stack tempStack) throws FullStackException, EmptyStackException {
			
			do {					
				top = gameStack.top();
				symmetricalTop = top;
				
				if(top <= gameStack.size()-1) {
					if(((gameStack.top() == symmetricalTop) && (top != 1) && (top == gameStack.size()-2))) {
						top = gameStack.top();
						if(top <= tempStack.size()) {
							for(int j = 0; j < top; j++) {
								gameStack.push(tempStack.pop()); //changing top
							}
						}
						else {
							return 0; //game ends
							//outFile.print("0\n");
							//break;
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
					return 0; //game ends
					//outFile.print("0\n");
					//break;
				}
			} while(gameStack.top() != 0);
			if(gameStack.top() == 0) {
				return 1; //game ends
				//outFile.print("1\n");
			}
			return 0;	
		}
	}

	public static void main(String[] args) {

		/*Declare Variables and Constants*/
		int stackSize;
		int count = 0;
		String line;
		
		/*Declare Stacks*/
		Stack gameStack = null;
		Stack tempStack = null;
		
		/*Declare Streams*/
		Scanner inFile = null;
		PrintWriter outFile = null;
		
		System.out.println("*********************************************");
		System.out.println("Welcome to the Strike Edge Zero Game!");
		System.out.println("*********************************************\n");
		
		System.out.println("Reading from in.txt...");
		
		try {
			
			/*Read and Write from Files*/
			inFile = new Scanner(new FileInputStream("in.txt"));
			outFile = new PrintWriter(new FileOutputStream("out.txt"));
			//System.out.println(count);
						
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
				System.out.println("Playing game StrikeEdgeZero...");
				outFile.print(stackStrikeEdgeZero.stackStrikeEdgeZero(gameStack, tempStack) + "\n");
				System.out.println("Game completed! ");
				
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
			
			System.out.println("All games from 'in.txt' completed, 'in.txt' closed and 'out.txt' closed.");
			System.out.println("*********************************************");
			System.out.println("Strike Edge Zero Program Complete!");
			System.out.println("*********************************************\n");
		}
	}
}
