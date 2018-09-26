/**
 * This program provides an implementation of the Deque interface
 * and demonstrates it.
 *
 * @author Pierce, Acuna
 * @version 1.0
 */
import java.util.NoSuchElementException;

/**
 * Issue at present: Dequeuefront is leaving dequeued numbers in list, so that
 * when the list adds to the front, they end up sticking in the middle
 *
 */
//TODO: implement.
public class PierceDeque<Item> implements Deque<Item> {
    private int count;
    private LinearNode<Item> front, back;

    /**
     * Creates an empty queue.
     */
    public PierceDeque() {
        count = 0;
        front = back = null;
    }

    /**
     * Adds one element to the front of this deque.
     * @param element the element to be added to the front of the deque
     */
    public void enqueueFront(Item element) {
        LinearNode<Item> node = new LinearNode<Item>(element);
        LinearNode<Item> temp = front;
        LinearNode<Item> next = new LinearNode<Item>();


        if (this.isEmpty()) {
            back = front = node;
        } else if(count == 1) {
            front = node;
            front.setNext(temp);
            back = temp;
            next = front.getNext();
            next.setPrev(front);
        } else {
            front = node;
            front.setNext(temp);
            next = front.getNext();
            next.setPrev(front);
        }

        count++;
    }

    /**
     * Adds one element to the back of this deque.
     * @param element the element to be added to the back of the deque
     */
    public void enqueueBack(Item element) {
        LinearNode<Item> node = new LinearNode<Item>(element);
        LinearNode<Item> temp = back;

        if(this.isEmpty()) {
            front = back = node;
        } else if(count == 1) {
            back.setNext(node);
            front = back;
            back = node;
            back.setPrev(temp);
        } else {
            back.setNext(node);
            back = node;
            back.setPrev(temp);
        }

        count++;
    }

    /**
     * Removes and returns the element at the front of this deque.
     * Throws an exception if the deque is empty.
     * @return the element at the front of this deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item dequeueFront() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("deque");
        }

        Item result = front.getElement();
        front.getNext().setPrev(null);
        front = front.getNext();
        count--;

        return result;
    }

    /**
     * Removes and returns the element at the back of this deque.
     * Throw an exception if the deque is empty.
     * @return the element at the back of the deque.
     * @throws NoSuchElementException if the deque is empty
     */
    public Item dequeueBack() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("deque");
        }

        LinearNode<Item> temp = back;

        if (front == back) {
            front = null;
            back = null;
            count--;
            return temp.getElement();
        }

        Item result = back.getElement();
        back = temp.getPrev();
        back.setNext(null);
        count--;

        if(this.isEmpty()) {
            front = null;
        }

        return result;
    }

    /**
     * Returns, without removing, the element at the front of this deque.
     * Should throw an exception if the deque is empty.
     * @return the first element in the deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item first() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("deque");
        }

        return front.getElement();
    }

    /**
     * Returns, without removing, the element at the back of this deque.
     * Should throw an exception if the deque is empty.
     * @return the last element in the deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item last() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("deque");
        }

        return back.getElement();
    }

    /**
     * Returns true if a given element exists inside the deque, false
     * otherwise.
     * @return if element exists in list
     */
    public boolean contains(Item element) {
        int i = 0;
        LinearNode<Item> test = new LinearNode<Item>(element);
        LinearNode<Item> queue_test = new LinearNode<Item>();
        queue_test = front;
        boolean found = false;

        while (i <= count) {
            if (test == queue_test) {
                found = true;
            } else {
                queue_test = queue_test.getNext();
            }
            i++;
        }

        return found;
    }

    /**
     * Returns true if this deque is empty and false otherwise.
     * @return if deque empty
     */
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the number of elements in this deque.
     * @return the number of elements in the deque
     */
    public int size() {
        return count;
    }

    /**
     * Returns a string representation of this deque. The back element
     * occurs first, and each element is separated by a space. If the
     * deque is empty, returns "empty".
     * @return the string representation of the deque
     */
    public String toString() {
        String out = "";
        int i = count;
        LinearNode<Item> temp = back;

        while (temp != null) {
            out = out + temp.getElement() + " ";
            temp = temp.getPrev();
        }

        return out;
    }

    /**
     * Program entry point for deque.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        PierceDeque<Integer> deque = new PierceDeque<>();

        //standard queue behavior
        deque.enqueueBack(3);
        deque.enqueueBack(7);
        deque.enqueueBack(4);
        deque.dequeueFront(); // Dequeue 3
        deque.enqueueBack(9);
        deque.enqueueBack(8);
        deque.dequeueFront(); // Dequeue 7
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());

        //deque features
        System.out.println(deque.dequeueFront()); // Dequeue 4
        deque.enqueueFront(1);
        deque.enqueueFront(11);
        deque.enqueueFront(3);
        deque.enqueueFront(5);
        System.out.println(deque.dequeueBack()); // Dequeue 8
        System.out.println(deque.dequeueBack()); // Dequeue 9
        System.out.println(deque.last());
        deque.dequeueFront(); // Dequeue 5
        deque.dequeueFront(); // Dequeue 3
        System.out.println(deque.first());
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());
    }
}
