package app.exercise.algebra;
import java.util.*;

/**
 * Class to do arithmetics with rationals <br>
 * This code ist free for everyone to use
 * @author Luis - 30.04.2024
 * @version 1.0f
 */
public class Rational extends BasisFraction {

    /**
     * class variable for nomiator
     */
    protected long n;
    /**
     * class variable for denomiator
     */
    protected long d;

    /**
     * Initialization with nominator and denominator
     * @param n nominator
     * @param d denominator
     */
    public Rational(long n, long d) {
	this.n = n;
	this.d = d;
	this.normalize();
    }

    /**
     * Getter for nominator
     * @return (long) nominator
     */
    public long getN() {
	return n;
    }

    /**
     * Getter for denominator
     * @return (long) denominator
     */
    public long getD() {
	return d;
    }

    /**
     * Setter for nominator and denominator
     * @param n nominator
     * @param d denominator
     */
    public void setND(long n, long d) {
	this.n = n;
	this.d = d;
	this.normalize();
    }

    /**
     * Caluculates the greatest common divisor for a and b
     * @param a (long) number 1
     * @param b (long) number 2
     * @return greatest common divisor of a and b
     */
    static long gcd(long a, long b) {
	if (b == 0) return a;
	return gcd(b, a % b);
    }

    /**
     * Normalizes a rational by dividing both nominator <br>
     * and denominator by the gcd and multiplying both by -1 <br>
     * if denominator is negative
     * @throws ArithmeticException
     */
    private void normalize() {
	if (d == 0)
	    throw new ArithmeticException();

	if (d < 0) {n *= -1; d *= -1;}

	long gcd = gcd(n, d);
	n /= gcd;
	d /= gcd;
    }

    /**
     * Overrides the toString() method to format rational correctly
     * @return formated string "nominator/denominator"
     */
    public String toString() {
	String s = n + ((d == 1) ? "" : "/" + d);
	return s;
    }

    /**
     * Overrides the equals() method to compare two rationals 
     * @param o object to be compared to this
     * @return boolean true if equals or false if not
     */
    public boolean equals(Rational o) {
	if (o == this) 
	    return true;

	if (!(o instanceof Rational)) 
	    return false;

	if (o == null) 
	    return false;

	Rational r = (Rational) o;

	this.normalize();
	o.normalize();

	return this.n == r.n && this.d == r.d;
    }

    /**
     * Overrides the hashCode() method
     * @return (int) nominator plus denominator
     */
    public int hashCode() {
	return (int) (n << 8) / (int) (d << 2);
    }

    /**
     * Overrides the clone() method 
     * @return new Rational object that has same (de)nominator
     */
    public Rational clone() {
	return new Rational(this.n, this.d);
    }
}
