public class DoubleNode<T> {
    private DoubleNode<T> next;
    private DoubleNode<T> prev;
    private T element;

    /**
     * Creates an empty node.
     */
    public DoubleNode() {
        next = null;
        prev = null;
        element = null;
    }

    /**
     * Creates a node storing the specified element.
     * @param elem element to be stored
     */
    public DoubleNode(T elem) {
        next = null;
        prev = null;
        element = elem;
    }

    /**
     * Returns the node that follows this one.
     * @return reference to next node
     */
    public DoubleNode getNext() {
        return next;
    }

    /**
     * Returns the node that precedes this one.
     * @return reference to the previous node
     */
    public DoubleNode getPrev() {
        return prev;
    }

    /**
     * Sets the node that follows this one
     * @param node node to follow this one
     */
    public void setNext(DoubleNode<T> node) {
        next = node;
    }

    /**
     * Sets the node that precedes this one
     * @param node node to precede this one
     */
    public void setPrev(DoubleNode<T> node) {
        prev = node;
    }

    /**
     * Returns the element stored in this node.
     * @return element stored at the node
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the element stored in this node.
     * @param elem element to be stored in this node
     */
    public void setElement(T elem) {
        element = elem;
    }
}