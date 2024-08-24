package app.exercise.algebra;

/**
 * Abstract class that defines some operations on Fractions <br>
 * leaves it to the inheriting class to define setND <br>
 * (a functino that sets (de)nominator)
 */
abstract class BasisFraction implements Fractional{

    /**
     * Setter for (de)nominator
     * @param numerator numerator
     * @param denominator denominator
     */
    protected abstract void setND(long numerator, long denominator);        

    /**
     * Addition on Rational original content of <br>
     * this obects gets overriden by result 
     * @param op Fractional that has to be added
     */ 
    public void add(Fractional op) {
	long nn = op.getN() * this.getD() + this.getN() * op.getD();
	long nd = op.getD() * this.getD();
	this.setND(nn, nd);
    }

    /**
     * Negates this Fraction
     * @return this where this is (-1)n/d
     */
    public Fractional negation() {
	setND((-1) * getN(), getD());
	return this;
    }

    /**
     * Substraction on Rational original content of <br>
     * this obects gets overriden by result 
     * @param op Fractional that has to be substracted 
     */ 
    public void sub(Fractional op) {
	this.add(op.negation());
	op.negation();
    }

    /**
     * Multiplication on Rational original content of <br>
     * this obects gets overriden by result 
     * @param op Fractional that has to be multiplied with 
     */ 
    public void mul(Fractional op) {
	this.setND(op.getN() * this.getN(), op.getD() * this.getD());
    }

    /**
     * swaps nominator and denominator to get the reciprocal of this
     * @return this where n and d are swapped
     */
    public Fractional reciprocal() {
	setND(getD(), getN());
	return this;
    }

    /**
     * Division on Rational original content of <br>
     * this obects gets overriden by result 
     * @param op Fractional that has to be divided by
     */ 
    public void div(Fractional op) {
	this.mul(op.reciprocal());
	op.reciprocal();
    }
}
