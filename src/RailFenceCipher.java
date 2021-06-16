
import java.util.Scanner;
import java.util.*;

//need this for window and action listeners(buttons)
import java.awt.*;
import java.awt.event.*;

public abstract class RailFenceCipher {
	
	
	//Will contain the cipher/plain text  that is encrypted/decrypted
	static char [] Ciphertext;
	static char [] Plaintext;
	
	//2D matrix for the "rail" where left is rows (key -> amount of rails) , column (size -> length of the text)
	static char [][]Rail;
	
	
	static int key;	//will contain the key for encryption or decryption (size of rails)

	static int size;//contains the size of the input so that the other array text or etc can be 
								//calculated easier with a small variable(less confusing)
	
	
	//----------------------------------------------------------------------------------------------------------------------//
	//																														//
	//											functions/methods to be used in main()										//
	//																														//
	//----------------------------------------------------------------------------------------------------------------------//
	
	
	//Program starting point in the main method to decide what user wants to do.
	
	public static void start()
	{   
		
		System.out.println("Type a 1 to Encrypt or 2 to Decrypt :)");
		
		getInput();
		
	}
	
	static void getInput()
	{
		Scanner EnDe = new Scanner(System.in);  //Scans user input to choose from encryption/decryption methods
		int x = EnDe.nextInt();
		if(x==1) {
			Encrypt();
		}
		
		else if(x==2) {
			Decrypt();
		}
		
		else {
			System.out.println("Oh no! Invalid character! Please try again "
					+ "\n\n Type a '1' to start Encrypt or a '2' to start Decrypt :)");
			
			getInput();
		}
		
	}
	
	//----------------------------------------------//
	//		Encryption and Decryption methods()		//
	//----------------------------------------------//
	
	static void Encrypt()
	{
		Scanner scan = new Scanner(System.in);  //input variables/scanner variables
		Scanner num = new Scanner(System.in);
		String text;
		System.out.println("Enter a Plaintext without spaces to get started");
		
		text = scan.next();
		size = text.length();
		
		//initialize the new array with size
		 char[] array = text.toCharArray(); 
		 
		//set plain text
		Plaintext = array;
		
		//set a key
		setKey();
		
		//make a matrix to give "rails" to Rail[][]
		char[][] matrix = new char[key][size];
		
		//makes Rails for matrix to pass on to Rail
		for(int q =0; q < key; q++) {
			for(int k = 0; k < size; k++) {
				matrix[q][k] = '.';
			}
		}
		
		//fill Rail
		Rail = matrix;
		
		System.out.println("This is your Plaintext: \n\n");
		
		displayText(Plaintext);
		
		System.out.println("\n\nThis is your Rail\n");
		
		//Encryption Rail
		EnRail();
		
		//makes ciphertext from the plaintext in the matrix
		setCipher();
		
		print2D(Rail);
		System.out.println("\nThis is your Ciphertext: \n");
		displayText(Ciphertext);
		
	}
	
	static void Decrypt()
	{
		Scanner scan = new Scanner(System.in);  //input variables/scanner variables
		Scanner num = new Scanner(System.in);
		String text;
		System.out.println("Enter a Ciphertext without spaces to get started");
		
		text = scan.next();
		size = text.length();
		
		//initialize the new array with size
		char[] array = text.toCharArray(); 
		
		//set plain text
		Ciphertext = array;
		
		//set a key
		setKey();
		
		//make a matrix to give "rails" to Rail[][]
		char[][] matrix = new char[key][size];
		
		//makeRails for matrix to pass on to Rail
		for(int q =0; q < key; q++) {
			for(int k = 0; k < size; k++) {
				matrix[q][k] = '.';
			}
		}
		
		//fill Rail
		Rail = matrix;
		
		//insert * symbols
		DeRail();
		System.out.println("This is your Rail:\n");
		
		//makes plaintext out of ciphertext in matrix
		setPlain();
		
		
		//see new Rails
		print2D(Rail);
		
		System.out.println("\nThis is your Plaintext:\n");
		
		displayText(Plaintext);
		
	}
	
	//------------------------------------------------------------------------------------------------------------------//
	//																													//
	//										set Plaintext and Cipher text methods										//
	//																													//
	//------------------------------------------------------------------------------------------------------------------//
	
	//method set a key
	static void setKey()
	{
		Scanner num = new Scanner(System.in);
		
		System.out.println("Enter an integer for your key.(must be >1)");
		key = num.nextInt();
		
		if(key<=1) {
			System.out.print("Your number is invalid! Please try again. ");
			setKey();
		}
		
	}
	
	static void setPlain()
	{
		//copied code from EnRail/DeRail (reused, in order to traverse the matrix for the plaintext)
		int q=0;
		boolean down = false;
		
		char pt[] = new char[size];
		//for loop to input the plain text into the matrix for
		//the length of the plain text
		for(int i = 0; i <size; i++)
		{
			
			if(q==0||q == key-1) {
				down=!down;//change to true or false (change directions)
			}
			
			pt[i] = Rail[q][i];

			//keep going down
			if(down) {
				q++;
			}
			//going up
			else
				q--;
		}
		
		Plaintext = pt;
	}
	
	static void setCipher()
	{
		char ct[] = new char[size];
		int a = 0;
		for(int r = 0; r < key; r++)
		{
			for(int m = 0; m < size;m++)
			{
				if(Rail[r][m] != '.')
				{
					ct[a]=Rail[r][m];
					a++;
				}
			}
		}
		Ciphertext = ct;
	}
	
	
	//----------------------------------------------------------------------------------------------//
	//																								//
	//									Character swapping rail methods								//
	//																								//
	//----------------------------------------------------------------------------------------------//
	
	//Encryption Rail used in encryption method
	static void EnRail()
	{
	int q=0;
	boolean down = false;
	
	//for loop to input the plain text into the matrix for
	//the length of the plain text
	for(int i = 0; i <size; i++)
	{
		
		if(q==0||q == key-1) {
			down=!down;//change to true or false (change directions)
		}
		
		Rail[q][i]=Plaintext[i];

		//keep going down
		if(down) {
			q++;
		}
		//going up
		else
			q--;
	}
		
	}
	
	//Decryption Rail used in decryption method
	static void DeRail()
	{
		int q=0;
		boolean down = false;
		
		//for loop to input the plain text into the matrix for
		//the length of the plain text
		for(int i = 0; i <size; i++)
		{
			
			if(q==0||q == key-1) {
				down=!down;//change to true or false (change directions)
			}
			
			Rail[q][i]='*';

			//keep going down
			if(down) {
				q++;
			}
			//going up
			else
				q--;
		}
		
		int a = 0;
		for(int r = 0; r < key; r++)
		{
			for(int m = 0; m < size;m++)
			{
				if(Rail[r][m] != '.')
				{
					Rail[r][m] = Ciphertext[a];
					a++;
				}
			}
		}
	}
	
	//------------------------------------------------------------------------------------------------------------------//
	//																													//
	//										Drawing/Print CLass and methods()											//
	//																													//
	//------------------------------------------------------------------------------------------------------------------//

	//used to display or print the matrix with symbols/dots or with plain/ciphertext
	static void print2D(char x[][])
	{
			 for (int i = 0; i < x.length; i++)
			 {
				 	System.out.print("|");
		            // Loop through all elements of current row 
		            for (int j = 0; j < x[i].length; j++)
		            {
		                System.out.print(x[i][j] + "|");
		            }
		            System.out.println(); //print in the next line
			 }
	}
	
	//display ciphertext or plaintext only, with bar '|' characters in between
	static void displayText(char x[]) {
		System.out.print("|");
		for (char c : x) { 
			System.out.print(c+"|");
		}
	}
}