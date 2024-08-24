package app.exercise.testing;

import app.exercise.algebra.Rational;
import app.exercise.visualtree.RedBlackTreeDrawer;
import app.exercise.adt.*;

import java.util.*;

public class BSTreeTester {
    public static void main(String[] args) {
        if (args.length % 2 != 0) {
            System.out.println("invalid args. ammount must be even");
            return;
        }

        long nums[] = new long[args.length];

	      Scanner s = new Scanner(System.in);

        for (int i = 0; i < args.length; i++) {
            nums[i] = Long.parseLong(args[i]);
        }
        
        BSTree<Rational> t = new BSTree<Rational>();
        Rational rats[]    = new Rational[args.length / 2];

        for (int i = 0; i < args.length; i += 2) {
            Rational r = new Rational(nums[i], nums[i + 1]);
            rats[i/2] = r;
            t.add(r);
        }
        
        RedBlackTreeDrawer<Rational> visual = new RedBlackTreeDrawer<Rational>();

        visual.draw(t.getRoot());
        System.out.println("Tree with all nodes");

	      s.nextLine();

        Rational[] ra = new Rational[t.size()];
        ra = t.toArray(ra);
        BSTree<Rational> sec1 = new BSTree<Rational>(); 
        BSTree<Rational> sec2 = new BSTree<Rational>();

        for (int i = 0; i < ra.length; i++) {
            if (i % 2 == 0) sec1.add((Rational) ra[i]);
            else            sec2.add((Rational) ra[i]);
        }
        System.out.println(Arrays.toString(ra));
        System.out.println(sec1);
        System.out.println(sec2);
        for (Rational r : t) System.out.print(r + " ");
        System.out.println();
        for (Rational r : sec1) System.out.print(r + " ");
        System.out.println();
        for (Rational r : sec2) System.out.print(r + " ");
        System.out.println();
	      s.nextLine();
        visual.draw(sec1.getRoot());
        System.out.println("Secondary Tree 1");
	      s.nextLine();
        visual.draw(sec2.getRoot());
        System.out.println("Secondary Tree 2");

        System.out.println("contains all: " + 
                          ((t.containsAll(sec1) && t.containsAll(sec2)) 
                           ? "true" : "false"));

	      s.nextLine();
        visual.draw(t.getRoot());
	      s.nextLine();

        if (rats.length >= 1) {
            t.remove(rats[0]);
      	    s.nextLine();
            visual.draw(t.getRoot());
            System.out.println("Originial Tree (after removing first arg)");

        }
        if (rats.length >  1) {
            t.remove(rats[rats.length - 1]);
      	    s.nextLine();
            visual.draw(t.getRoot());
            System.out.println("Originial Tree (after removing last arg)");
        }

        Rational sra[] = new Rational[t.size()];
        sra = t.toArray(sra);
        System.out.println("new Tree looks like this: " + Arrays.toString(sra));

        Rational l = t.getMin();
        Rational h = t.getMax();

        if (l == null || h == null) {
            System.out.println("Now the tree is empty . :C");
            return;
        }

        System.out.println("lowest is " + l);
        System.out.println("highest is " + h);
        
        long a = l.getN();
        long b = l.getD();
        long c = h.getN();
        long d = h.getD();

        Random r = new Random();
        int rands[] = r.ints(100, 0, 1001).toArray();

        for (int i = 0; i < 100; i++) {
            Rational tmp = new Rational(rands[i] * ((c*b)-(a*d)), (d*b)*1000);
            tmp.add(l);
            // System.out.println(tmp + " " + ((t.contains(tmp)) ? "yes" : "no"));
            t.remove(tmp);
        }
      	s.nextLine();
        visual.draw(t.getRoot()); 
        System.out.println("Tree after removing some random nodes");
        s.close();
    }
} 
