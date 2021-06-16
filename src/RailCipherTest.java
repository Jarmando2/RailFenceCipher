import java.util.Scanner;
import java.util.*; 

public class RailCipherTest extends RailFenceCipher{
	

	public static void main(String[] args) {
		
		System.out.println("Hello & welcome to Rail Fence Cipher, let's get started!\n");
		start();
		menu();
		
	}
 public static void menu()
 {
	 System.out.println("\n\nWould you like to use the program again?('y' or 'Y' for yes | 'n' or 'N' for no)");
	 Scanner scan = new Scanner(System.in);
	 
	 char ask = scan.next().charAt(0);
	 
	 if(ask == 'y'||ask =='Y'){
		 System.out.println();
		 main(null);
	 }
	 else if(ask =='n'||ask == 'N') {
		 System.out.println("Thank you for using the program :)");
	 }
	 
	 else
	 {
		 System.out.println("Invalid character! Please try again.\n");
		 menu();
	 }
 }
}
