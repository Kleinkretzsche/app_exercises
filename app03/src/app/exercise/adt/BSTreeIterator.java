package app.exercise.adt;

import java.util.*;
import app.exercise.adt.BSTree;

public class BSTreeIterator<E extends Comparable<E>> 
    implements Iterator<E> 
{

    boolean removed;
    Stack<E> s;

    public BSTreeIterator(Node<E> n) {
        removed = false;
        s = new Stack<>();
        inOrder(n);
    }

    public void inOrder(Node<E> n) {
        if (n == null) return;
        inOrder(n.right);
        s.push(n.content);
        inOrder(n.left);
    }

    public boolean hasNext() {
	    return !s.empty();
    }

    public E next() {
      removed = false;
	    if (hasNext()) {
	        return s.pop();
      }
	    else 
	        throw new NoSuchElementException("no more elements :C");
    }

    public void remove() { 
        if (removed == false) s.pop();
        else throw new IllegalStateException("can only remove once");
    }
}
