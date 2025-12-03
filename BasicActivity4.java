/**
 * 
 */
package Training;
import java.util.Scanner;
/**
 * 
 */
public class BasicActivity4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your age: ");
		int age = scanner.nextInt();
		
	if(age<18) {
		System.out.println("Minor");	
	}
	else if(age<=59) {
		System.out.println("Adult");	
	}
	else {
		System.out.println("Senior");
	}
		scanner.close();
	}
	
}
