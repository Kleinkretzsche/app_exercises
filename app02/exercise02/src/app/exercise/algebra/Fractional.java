package app.exercise.algebra;

/**
 * Interface for classes that want to implement some <br>
 * fractional behavior
 */
public interface Fractional {
    /**
     * function that gets the nominator of the class
     * @return numerator
     */
    long getN();

    /**
     * Gets the denominator of the class
     * @return denominator
     */
    long getD();

    /**
     * Changes this object to this added to operand
     * @param operand fraction to be added to this
     */
    void add(Fractional operand);

    /**
     * Changes this object to this subtsracted from operand
     * @param operand fraction to be substracted from this
     */
    void sub(Fractional operand);

    /**
     * Changes this object to this multiplied by operand
     * @param operand fraction to be multiplied by this
     */
    void mul(Fractional operand);

    /**
     * Changes this object to this divided by operand
     * @param operand fraction to be divided by this
     */
    void div(Fractional operand);

    /**
     * gets negation of this
     * @return negation of this
     */
    Fractional negation();

    /**
     * gets reciprocal of this
     * @return reciprocal of this
     */
    Fractional reciprocal();
}
