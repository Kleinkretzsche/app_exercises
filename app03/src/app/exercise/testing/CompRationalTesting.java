package app.exercise.testing;

import app.exercise.algebra.CompRational;

/**
 * class to test CompRational
 */
public class CompRationalTesting 
{
    /**
     * tests if 1/2 == 1/2 , 1/2 == 1/3 and 1/2 == 1/1
     */
    public static void main(String[] args) 
    {
	CompRational a = new CompRational(1, 2);    
	CompRational b = new CompRational(1, 2);    
	CompRational c = new CompRational(1, 3);    
	CompRational d = new CompRational(1, 1);    

	System.out.println(a.compareTo(b));
	System.out.println(a.compareTo(c));
	System.out.println(a.compareTo(d));
    }
}
