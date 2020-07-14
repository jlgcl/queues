/* *****************************************************************************
 *  Name:
 *  Date: July 12, 2020
 *  Description:
 - constant worst-case time, O(1)
 - implement Deque in linked-list & RandomizedQueue in array
 * take care of loitering & consider resizing array
 * Reference programs: ResizingArrayStack.java & LinkedStack.java
 *
 * REMEMBER:
    * first.next is a data structure for that node.
    * iterator() allow clients to iterate through objects of whatever type is provided by our client.
        * this can be replaced with the foreach method.
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

// in the main method, call Deque<String> etc.
public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int n;  // stack size

    // defines what elements each node contains
    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node oldfirst
                = first;  // creates a new Node called "oldfirst" & assign to it the current first.
        first = new Node(); // create a new first node (input)
        first.item = item;  // assign input item to the new node
        first.next = oldfirst;  // after the new node is the old node.
        if (isEmpty()) first = last;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        // may need to see the 'last' reference after node is removed from last.
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) last = first;
        else {
            oldlast.next = last;
            last.prev = oldlast;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Stack Underflow");
        Node oldfirst = first;
        Item item
                = first.item;     // initializes & save the first.item that is to be deleted to be returned in this method.
        first = first.next;
        oldfirst = null;
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Stack Underflow");
        Item item = last.item;
        last.item = null;
        last = last.prev;
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    // need to have a private class that implements the iteration (hasNext(), remove() & next()) & called inside iterator().
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}
