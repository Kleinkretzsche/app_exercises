package app.exercise.algebra;

/**
 * class that extends the Rational class to make it comparable
 */
public class CompRational extends Rational implements Comparable<Rational> 
{

    /**
     * constructor that just uses Rational(int n, int d)
     * @param n numerator
     * @param d denominator
     */
    public CompRational(int n, int d) 
    {
	super(n, d);
    }

    /**
     * Override to let two show equality between Rational objects<br>
     * divides numerator and denominator and compares the resulting double values
     * @param other Rational to be compared to
     */
    @Override
    public int compareTo(Rational other) 
    {
	double t = (double) this.n  / (double)  this.d;
	double o = (double) other.n / (double) other.d;
	if (t >  o) return 1;
	if (t <  o) return -1;
	return 0;
    }
}
