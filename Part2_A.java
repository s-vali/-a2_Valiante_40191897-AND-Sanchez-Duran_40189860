package Assi2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Part2_A {
	
	/*
	 * This class uses a recursive method to implement the game StrikeEdgeZero
	 */
	
	public static int recStrikeEdgeZero(int initial, int size, int A[], ArrayList<Integer> seen) {
		int newInitial;	
		int goRight = initial + A[initial];
		int goLeft = initial - A[initial];
		
		//Base Cases
		if (A[initial]==0) {	
			return 1;	//you win!
		} 
		if ((goRight>=size)&&(goLeft<0)) {
			return 0;	//you lose --> can't move without violating boundaries of array
		} 
		if (seen.contains(initial)) {	//checks if this index has already been visited
			return 0;	//you lose --> going in circles	
		
		}
		
		seen.add(initial);	//adding index to array list
		
		//Making Recursive Calls	
		if (goRight < size) {
			newInitial = goRight;
			if ((A[goRight]==A[initial])&& (goRight + A[goRight] >=size) && (goLeft>0)) {
				newInitial = goLeft;
			}
			return recStrikeEdgeZero(newInitial,size,A,seen);
		} else {	
				newInitial = goLeft;
				return recStrikeEdgeZero(newInitial,size,A,seen);
		} 
	}	//end of method
	
	
	
	public static void main(String[] args) {
		
		//Scanner declaration
		Scanner keyIn = new Scanner(System.in);
				
		//Variable declaration
		String fileName;
				
		//Welcome Message
		System.out.println("*********************************************");
		System.out.println("\n Welcome to the Strike Edge Zero Game!");
		System.out.println("\n*********************************************");
		
		//Prompting user for name of the file
		System.out.print("\nWhat is the name of the file? ");
		fileName = keyIn.nextLine();
				
		Scanner scan = null;
				
		//Opening file
		try {
			scan = new Scanner(new FileInputStream(fileName));	//opening file
		} catch (FileNotFoundException e) {
			System.out.println("Could not open file " + fileName + " for reading");
			System.out.println("Please check if file exists. Program will now terminate.");
			System.exit(0);
		}
		
		//More variable declarations
		int amount=0;
		int size=0;
		int initial=0;
		int A[]=new int[0];
		
		int outputs[] = new int[0];
		
		//Reading file
		try {
			amount = scan.nextInt();	//reads amount of lines/arrays
			outputs = new int[amount];
			
			for (int j=0; j<amount;j++) {
				size = scan.nextInt();
				initial = scan.nextInt();
				
				ArrayList<Integer> seen = new ArrayList<Integer>();	//Array List Declaration
				
				A = new int[size];	//creating and initializing array with size
		
				for (int i=0; i<size-1; i++) {	//this for-loop adds the zero at the end
					A[size-1]=0;
					A[i]=scan.nextInt();
				}	//end of inner for-loop	
				outputs[j]= recStrikeEdgeZero(initial,size,A,seen);	
				//System.out.println(seen);		//printing out array list
			}	//end of outer for-loop	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Could not read file!");
			System.out.println("Program will now terminate.");
			System.exit(0);
		}
		
		//Creating file!
		try {
			FileWriter fw1 = new FileWriter("C:\\Users\\karin\\OneDrive\\Documents\\Fall_2022\\new_COMP352\\A2_Files\\A_out.txt");
			fw1.write("Output: \n");
			for (int m=0; m<amount; m++) {	
				String temp = String.valueOf(outputs[m]);
				fw1.write(temp + "\n");
			}
			fw1.close();
		} catch (Exception e) {
			System.out.println("Could not create file!");
			System.out.println("Program will now terminate.");
			System.exit(0);
		}
		
		System.out.println("Thanks for playing!");
		//System.out.println(seen);
		keyIn.close();
		System.exit(0);
	}	//end of main

}	//end of class
