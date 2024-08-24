package app.exercise.adt;
import  app.exercise.visualtree.DrawableTreeElement;

public class Node<E> implements DrawableTreeElement<E>{
    public E value;
    public Node left, right;

    public Node(E content) {
        this.value = value;
        left = right = null;
    }

    public Node<E> getRight() { return right; }
    public Node<E> getLeft()  { return left; }
    public E       getValue() { return content; }
    public boolean isRed()    { return false; }
}
