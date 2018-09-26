public class DoubleOrderedList<T extends Comparable<T>> extends DoubleList<T> implements OrderedListADT<T> {
    public DoubleOrderedList() {
        super();
    }

    public void add(T element) {
        if (!(element instanceof Comparable)) {
            throw new NonComparableElementException("DoubleOrderedList");
        }

        DoubleNode<T> adder = new DoubleNode<T>(element);


        DoubleNode<T> prev;
        DoubleNode<T> next;
        Comparable<T> comparableElement = (Comparable<T>)element;
        DoubleNode<T> temp;
        DoubleNode<T> tempNext;

        if(isEmpty()) {
            head = tail = adder;
        } else if (comparableElement.compareTo(tail.getElement()) > 0) {
            prev = tail;
            tail = adder;
            tail.setPrev(prev);
            prev.setNext(tail);
        } else if (comparableElement.compareTo(head.getElement()) < 0) {
            next = head;
            head = adder;
            head.setNext(next);
            next.setPrev(head);
        } else {
            temp = head;
            tempNext = head.getNext();
            while (temp.getNext() != null) {
                if (comparableElement.compareTo(temp.getElement()) > 0 && comparableElement.compareTo(tempNext.getElement()) < 0) {
                    temp.setNext(adder);
                    adder.setPrev(temp);
                    adder.setNext(tempNext);
                    tempNext.setPrev(adder);
                    break;
                } else {
                    temp = tempNext;
                    tempNext = temp.getNext();
                }
            }
        }
        count++;
        modCount++;
    }

}
