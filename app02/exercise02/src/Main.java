import app.exercise.algebra.*;
import app.exercise.testing.*;
import java.util.Scanner;

/**
 * Main Class that serves as an entry point<br>
 */
public class Main {
    /**
     * Main function that can either call test for Rational or RPN
     */
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);	
	System.out.println("Test Rational (1) or RPN (2)");
	String s = sc.nextLine();
	int n = 0;

	try {
	    n = Integer.parseInt(s);
	} catch (NumberFormatException e){
	    System.out.println("Enter either 1 or 2");
	}

	if (n == 1) 
	    RationalTesting.test();
	if (n == 2) 
	    RPN.test();
    }
}
