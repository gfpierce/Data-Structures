import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class DoubleList<T> implements ListADT<T>, Iterable<T> {
    protected int count;
    protected DoubleNode<T> head, tail;
    protected int modCount;

    /**
     * Creates an empty list.
     */
    public DoubleList() {
        count = 0;
        head = tail = null;
        modCount = 0;
    }

    /**
     * Removes and returns the first element from this list.
     *
     * @return the first element from this list
     */
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedList");
        }

        T result = head.getElement();
        DoubleNode<T> next = head.getNext();
        head.getNext().setPrev(null);
        head = next;
        count--;
        modCount++;

        return result;
    }

    /**
     * Removes and returns the last element from this list.
     *
     * @return the last element from this list
     */
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedList");
        }

        DoubleNode<T> temp = tail;
        DoubleNode<T> tempPrev = tail.getPrev();

        if (head == tail) {
            head = null;
            tail = null;
            count--;
            return temp.getElement();
        }

        T result = tail.getElement();
        tail = tempPrev;
        tail.setNext(null);
        count--;
        modCount++;

        if(isEmpty()) {
            head = null;
        }

        return result;
    }

    /**
     * Removes and returns the specified element from this list.
     *
     * @param element the element to be removed from the list
     */
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedList");
        }

        boolean found = false;
        DoubleNode<T> previous = null;
        DoubleNode<T> current = head;
        DoubleNode<T> next;
        DoubleNode<T> prev;

        while (current != null && !found) {
            if (element.equals(current.getElement())) {
                found = true;
            } else {
                previous = current;
                current = current.getNext();
            }
        }

        T result = current.getElement();

        if (!found) {
            throw new ElementNotFoundException("LinkedList");
        }

        if (size() == 1) {
            head = tail = null;
        } else if (current.equals(head)) {
            next = current.getNext();
            next.setPrev(null);
            head = next;
        } else if (current.equals(tail)) {
            prev = current.getPrev();
            tail = prev;
            tail.setNext(null);
        } else {
            //Does this establish both links?
            prev = current.getPrev();
            next = current.getNext();

            next.setPrev(prev);
            prev.setNext(next);
            current = null;
        }

        count--;
        modCount++;
        return result;
    }

    /**
     * Returns a reference to the first element in this list.
     *
     * @return a reference to the first element in this list
     */
    public T first() throws ElementNotFoundException {
        if (isEmpty()) {
            throw new ElementNotFoundException("LinkedList");
        }
        return head.getElement();
    }

    /**
     * Returns a reference to the last element in this list.
     *
     * @return a reference to the last element in this list
     */
    public T last() {
        if(isEmpty()) {
            throw new ElementNotFoundException("LinkedList");
        }
        return tail.getElement();
    }

    /**
     * Returns true if this list contains the specified target element.
     *
     * @param target the target that is being sought in the list
     * @return true if the list contains this element
     */
    public boolean contains(T target) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedList");
        }

        boolean found = false;
        DoubleNode<T> current = head;

        while (current != null && !found) {
            if (target.equals(current.getElement())) {
                found = true;
            } else {
                current = current.getNext();
            }
        }

        return found;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }

        return false;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the integer representation of number of elements in this list
     */
    public int size() {
        return count;
    }

    /**
     * Returns a string representation of this list.
     *
     * @return a string representation of this list
     */
    public String toString() {
        String out = "";
        int i = count;
        DoubleNode<T> temp = head;

        while (temp != null) {
            out = out + temp.getElement() + " ";
            temp = temp.getNext();
        }

        return out;
    }

    @Override
    public Iterator<T> iterator() {
        final DoubleList<T> list = this;
        return new Iterator<T>() {
            final DoubleNode<T> headNode = list.head;
            DoubleNode<T> currentNode = null;
            @Override
            public boolean hasNext() {
                if (list.isEmpty()) {
                    return false;
                } else if (currentNode == null) {
                    return true;
                } else if (currentNode == list.tail) {
                    return false;
                } return true;
            }
            @Override
            public T next() {
                if (list.isEmpty()) {
                    throw new NoSuchElementException("DoubleList");
                } else if (currentNode == null) {
                    this.currentNode = headNode;
                    return currentNode.getElement();
                } else if (currentNode.getNext() == null) {
                    throw new NoSuchElementException("DoubleList");
                }
                this.currentNode = currentNode.getNext();
                return currentNode.getElement();
            }
        };
    }
}
