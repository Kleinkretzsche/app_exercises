package app.exercise.testing;
import java.util.*;
import app.exercise.algebra.Rational;

/**
 * Class to interprete Expressions in reverse polish <br>
 * notation using Rationals
 */
public class RPN {

    /**
     * checks if a string is numeric by trying to parse it to a long
     * @param s String to be tested
     * @return true if s can be converted to long and false otherwise
     */
    public static boolean isNumeric(String s) {
	if (s == null)
    	    return false;
    	try {
    	    long d = Long.parseLong(s);
    	} catch (NumberFormatException nfe) {
    	    return false;
    	}
    	return true;
    } 

    /**
     * checks if a string is an operator by checking if length == 1 <br>
     * and first char or '+' or '-' or '*' or '/'
     * @param s String to be tested
     * @return true if s is an operator and false otherwise
     */
    public static boolean isOperator(String s) {
	if (s == null) 
	    return false;

	char c = s.charAt(0);

	return  s.length() == 1 && 
		c 	   == '+' || 
		c 	   == '-' || 
		c 	   == '*' ||
		c 	   == '/';
    }

    /**
     * Interactive function to evaluate RPN-expression <br>
     * that scanns for the expression, splits it by whitespace <br>
     * and evaluates it if possible <br>
     * on error prints a helper text and returns
     */
    public static void main(String[] args) {
	System.out.println("==RPNTest==========================");
	System.out.println("enter valid expression:");

	Scanner sc = new Scanner(System.in);

	String s = sc.nextLine();
	String[] xs = s.split("\\s+");
	
	Stack<Rational> rat_stack = new Stack<Rational>();
	long n;
	Rational r, a, b;

	for (String str : xs) {
	    if (isNumeric(str)) {
		n = Long.parseLong(str);
		r = new Rational(n, 1);
		rat_stack.push(r);
	    }
	    else if (isOperator(str)) {
		a = new Rational(0,1);
		b = new Rational(0,1);
		try {
		    b = rat_stack.pop();
		    a = rat_stack.pop();
		} catch (EmptyStackException e) {
		    System.out.println("empty stack!!");
		}
		
		switch(str) {
		    case "+":
			a.add(b);
			break;
		    case "-":
			a.sub(b);
			break;
		    case "*":
			a.mul(b);
			break;
		    case "/":
			a.div(b);
			break;
		}

		rat_stack.push(a);
	    }
	    else {
		System.out.println("you didn't write proper RPN-expression... idiot");
		return;
	    }
	}
	System.out.println("Result: " + rat_stack.pop());
    }
}
