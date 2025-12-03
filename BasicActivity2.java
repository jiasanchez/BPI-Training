/**
 * 
 */
package Training;
import java.util.Scanner;
/**
 * 
 */
public class BasicActivity2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your age: ");
		String input = scanner.next();
		int intage= Integer.parseInt(input);
		double doublage = Double.parseDouble(String.valueOf(intage));
		
		System.out.println("Your age in int: " + intage);
		System.out.println("Your age in doubke: "+ doublage);

		scanner.close();
	}

}
