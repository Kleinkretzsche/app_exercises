package app.exercise.adt;

import java.util.*;
import java.lang.Comparable;
import app.exercise.adt.Node;

public class BSTree<E extends Comparable<E>> 
    extends AbstractCollection<E> {

    private Node<E> root;

    public BSTree() {
        root = null;
    }

    public Node<E> getRoot() { return root; }

    public E getMin() {
        if (root == null) return null;
        return min(root).getValue();
    }

    public E getMax() {
        if (root == null) return null;
        return max(root).getValue();
    }

    private Node<E> min(Node<E> curr) {
        if (curr.left == null) return curr;
        else return min(curr.left);
    }

    private Node<E> max(Node<E> curr) {
        if (curr.right == null) return curr;
        else return min(curr.right);
    }

    private Node<E> deleteMin(Node<E> n) {
        if (n.left == null) return n.right;
        n.left = deleteMin(n.left);
        return n;
    }

    public boolean contains(E e) {
        if (e == null) return true;
        return contains(root, e);
    }

    private boolean contains(Node<E> curr, E e) {
        if (curr == null) return false;
        int cmp = e.compareTo(curr.content);
        if (cmp < 0) return contains(curr.left, e);
        if (cmp > 0) return contains(curr.right, e);
        return true;
    }

    public boolean add(E e) {
        Node<E> rootC = root;
        try {
            root = add(root, e);
        } catch (IllegalArgumentException err) {
            root = rootC;
            return false;
        }
        return true;
    }

    private Node<E> add(Node<E> curr, E e) {
        if (curr == null) return new Node<>(e);
        int cmp = e.compareTo(curr.content);
        if      (cmp < 0) curr.left  = add(curr.left, e);
        else if (cmp > 0) curr.right = add(curr.right, e);
        else throw new IllegalArgumentException("uniqe elements please");
        return curr;
    }

    public boolean remove(E e) {
        boolean hadE = contains(e);
        root = remove(root, e);
        return hadE;
    }

    private Node<E> remove(Node<E> curr, E e) {
        if (curr == null) return null;
        int cmp = e.compareTo(curr.content);
        if      (cmp < 0) curr.left  = remove(curr.left, e);
        else if (cmp > 0) curr.right = remove(curr.right, e);
        else {
            if (curr.right == null) return curr.left;
            if (curr.left  == null) return curr.right;
            Node<E> tmp = curr;
            curr = min(tmp.right);
            curr.right = deleteMin(tmp.right);
            curr.left  = tmp.left;
        }
        return curr;
    }

    public BSTreeIterator<E> iterator() { return new BSTreeIterator<>(root); }

    public int size() {
        return size(root);
    }

    public int size(Node<E> n) {
        if (n == null) return 0;
        return 1 + size(n.left) + size(n.right);
    }

}
