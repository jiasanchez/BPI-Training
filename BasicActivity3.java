/**
 * 
 */
package Training;
import java.util.Scanner;
/**
 * 
 */
public class BasicActivity3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Scanner scanner = new Scanner(System.in);
	
	System.out.print("Enter First Integer: ");
	int int1 = scanner.nextInt();
	System.out.print("Enter Second Integer: ");
	int int2 = scanner.nextInt();
	
	int total = sum(int1,int2);
	int diff = subtract(int1,int2);
	int product = multiply(int1,int2);
	
	System.out.println("Sum: " + total);
	System.out.println("Difference: " + diff);
	System.out.println("Product: " + product);
	scanner.close();
	}
	
	public static int sum(int a,int b) {
		return a + b;
	}
	public static int subtract(int a,int b) {
		return a - b;
	}
	public static int multiply(int a,int b) {
		return a * b;
	}
	
}
