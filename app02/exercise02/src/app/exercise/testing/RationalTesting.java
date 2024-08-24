package app.exercise.testing;
import java.util.Scanner;
import app.exercise.algebra.Rational;

/**
 * Class to test the Rational class
 */
public class RationalTesting {

    /**
     * Reads 4 longs and executes all of the defined class methods <br>
     * returns early, if improper arguments are passed on StdIn
     */
    public static void test() {

	System.out.println("==RationalTesting==================");
	System.out.println("Enter 4 numbers (type long)");

	Scanner sc = new Scanner(System.in);
	String s = sc.nextLine();
	String[] xs = s.split("\\s+");
	if (xs.length != 4) {
	    System.out.println("Enter 4 longs seperated by whitspace please...");
	    return;
	}
	long na = Long.parseLong(xs[0]);
	long da = Long.parseLong(xs[1]);
	long nb = Long.parseLong(xs[2]);
	long db = Long.parseLong(xs[3]);

	System.out.printf("%d %d and %d %d%n", na, da, nb, db);

	Rational a = new Rational(na, da);
	Rational b = new Rational(nb, db);

	System.out.println("==> " + a + " and " + b);
	System.out.println("hashcode a: " + a.hashCode());
	System.out.println("hashcode b: " + b.hashCode());

	System.out.println(
		"a is " + (a.equals(b) ? "" : "not ") + "equal to b");

	Rational c = a.clone();
	c.add(b);
	Rational d = a.clone();
	d.sub(b);
	Rational e = a.clone();
	e.mul(b);
	Rational f = a.clone();
	f.div(b);

	System.out.println(a + " + " + b + " = " + c);
	System.out.println(a + " - " + b + " = " + d);
	System.out.println(a + " * " + b + " = " + e);
	System.out.println(a + " / " + b + " = " + f);
	System.out.println();
    }
}
