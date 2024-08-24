import java.util.Arrays;

public class PolynomialGF2 {

    // constants for ZERO and ONE polynomial
    // i find it pretty stupid, that the zero polynomial isn't
    // just an array with element false, but ok
    private static final boolean ZERO[] = null;
    private static final boolean ONE[]  = new boolean[]{true};

    // fields for coefficients and degree n
    private final boolean coefficients[];
    private int n;

    // default initializer
    public PolynomialGF2() {
	coefficients = ONE;
	n = 0;
    }

    // initializer to get Polynomial from Array
    public PolynomialGF2(boolean[] cs) {
	coefficients = trim(cs);
	if (coefficients == ZERO) {
	    n = 0;
	} else {
	    n = coefficients.length - 1;
	}
    }

    // helper method to quickly generate polynomials from a string.
    // WARNING: can easily fail if provided an invalid string!!
    public static PolynomialGF2 fromString(String cs) {
	if (cs == null || cs == "") return new PolynomialGF2(ZERO);
	else {
	    boolean coef[] = new boolean[cs.length()];
	    for (int i = 0 ; i < cs.length(); i++) {
		if (cs.charAt(i) == '0') coef[i] = false;
		else coef[i] = true;
	    }
	    boolean c[] = trim(coef);
	    return new PolynomialGF2(c);
	}
    }

    // check if Polynomial is ONE
    public boolean isOne() {
	return Arrays.equals(this.coefficients, ONE);
    }

    // check if Polynomial is ZERO
    public boolean isZero() {
	return Arrays.equals(this.coefficients, ZERO) ||
	    Arrays.equals(this.coefficients, new boolean[]{false});
    }

    // self explanitory
    public boolean[] toArray() {
	if (this.isZero()) return ZERO;
	boolean c[] = new boolean[coefficients.length];
	for (int i = 0 ; i < coefficients.length; i++) {
	    c[i] = coefficients[i];
	}
	return c;
    }

    // removes unnecessary coefficients 
    // (i.e. trims off all falses from the right until true)
    public static boolean[] trim(boolean[] a) {
	if (a == null) return null;
	int len = a.length;
	int shorten_by = 0;
	for (int i = len - 1; i >= 0; i--) {
	    if (a[i] == false) {
		shorten_by++;
	    } else {
		break;
	    }
	}
	if (len - shorten_by == 0) return null;
	boolean n[] = new boolean[len - shorten_by];
	for (int i = 0; i < n.length; i++) {
	    n[i] = a[i];
	}
	return n;
    }

    // generates new polynomial multiplied by x^k
    public PolynomialGF2 shift(int k) {
	if (k < 0)  return null;
	if (k == 0) return this.clone();
	boolean a[] = new boolean[this.n + k + 1];
	for (int i = 0; i <= this.n; i++) {
	    a[i + k] = this.coefficients[i];
	}
	return new PolynomialGF2(a);
    }

    // returns new array of booleans to make xoring 
    // easier in plus-method
    public static boolean[] stretch_to(boolean a[], int len) {
	if (len < a.length) return null;    
	boolean stretched[] = new boolean[len];
	System.arraycopy(a, 0, stretched, 0, a.length);
	return stretched;
    }

    // adds this and p and returns new polynomial
    public PolynomialGF2 plus(PolynomialGF2 p) {
	if (p == null) return null;
	if (p.isZero()) return this.clone();
	if (this.isZero()) return p.clone();

	PolynomialGF2 smaller = (this.n < p.n) ? this.clone() : p.clone();
	PolynomialGF2 bigger = (this.n >= p.n) ? this.clone() : p.clone();

	boolean stretched[] = 
	    stretch_to(smaller.coefficients, bigger.n + 1);
	boolean ret[] = new boolean[bigger.n + 1];

	for (int i = 0; i <= bigger.n; i++)
	    ret[i] = bigger.coefficients[i] ^ stretched[i];

	return new PolynomialGF2(ret);
    }

    // multiplies this and p and returns new polynomial
    public PolynomialGF2 mult(PolynomialGF2 p) {
	if (this.isZero() || p.isZero()) return new PolynomialGF2(ZERO);
	int new_n = p.n + this.n;
	PolynomialGF2 ret = new PolynomialGF2(new boolean[new_n + 1]); 
	for (int i = 0; i <= this.n; i++) {
	    if (this.coefficients[i]) 
		ret = ret.plus(p.shift(i)); 
	}
	return ret;
    }

    // returns this^k
    public PolynomialGF2 power(int k) {
	if (k == 0) return new PolynomialGF2(ONE);
	PolynomialGF2 p = this.clone();
	for (int i = 1; i < k; i++) {
	    p = p.mult(this);
	}
	return p;
    }

    // returns new polynomial of remainder from this / p
    public PolynomialGF2 mod(PolynomialGF2 p) {
	PolynomialGF2 a = this.clone();
	PolynomialGF2 b = p.clone();
	while (a.n >= b.n) {
	    b = b.shift(a.n - b.n);
	    a = a.plus(b);
	    b = p.clone();
	}
	return a.clone();
    }

    // overrides equals method 
    public boolean equals(Object o) {
	if (o == null) return false;
	if (o == this) return true;
	if (!(o instanceof PolynomialGF2)) return false;
	final PolynomialGF2 other = (PolynomialGF2) o;
	if (n != other.n) return false;
	for (int i = 0; i <= n; i++) {
	    if (coefficients[i] != other.coefficients[i]) return false;
	}
	return true;
    }

    // overrides clone method 
    public PolynomialGF2 clone() {
	return new PolynomialGF2(this.toArray()); 
    }

    // generates hashcode
    public int hashCode() {
	int mult = 1;
	int acc = 0;
	for (int i = 0; i <= n; i++) {
	    acc += (coefficients[i] ? 1 : 0) * mult;
	    mult *= 2;
	}
	return acc;
    }

    // overrides toString method
    public String toString() {
	if (this.isZero()) return "0";
	if (this.isOne()) return "1";
	if (n == 1 && coefficients[0] == false) return "x";
	if (n == 1 && coefficients[0]) return "x + 1";
	String s = "x^" + n;
	for (int i = n-1; i > 1; i--) {
	    if (coefficients[i]) {
		s += (" + x^" + i);
	    } 
	}
	if (n > 1  && coefficients[1]) s += " + x";
	if (n >= 0 && coefficients[0]) s += " + 1";
	return s;
    }

    public static void test1() {
	PolynomialGF2 irreducible = fromString("1101");
	PolynomialGF2 generator = fromString("01");
	System.out.println("i | hash | x^i");
	System.out.println("----------------------------");
	for (int i = 0; i < 7; i++) {
	    System.out.printf("%d |  %02d  | %s\n", 
		    i, 
		    generator.power(i).mod(irreducible).hashCode(),
		    generator.power(i).mod(irreducible).toString());
	}
	System.out.println();
    }

    public static void test2() {
	System.out.println("  |  0   1   2   3   4   5   6   7   8   9   a   b   c   d   e   f");
	System.out.println("------------------------------------------------------------------");

	PolynomialGF2 irreducible = fromString("110110001");
	PolynomialGF2 original = fromString("11");

	for (int i = 0; i < 16; i++) {
	    System.out.printf("%x | ", i);
	    for (int j = 0; j < 16; j++) {
		int pot = i * 16 + j;
		System.out.printf("%02x  ", 
			original.power(pot).mod(irreducible).hashCode());
	    }
	    System.out.println();
	}
	System.out.println();
    }

    public static void main(String[] args) {
	test1();
	test2();
    }
}
