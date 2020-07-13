/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description: Practice stacks/queues with linked-list/array implementations + iterators
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

// Queue enqueue
public class Practice<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;   // returns true if first == null;
    }

    public int size() {
        return N;
    }

    // input to last
    public void enqueue(Item item) {
        Node oldlast = last;    // defines oldlast node
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;    // adding a new item; so the new stack is never empty.
        else oldlast.next = last;
        N++;
    }

    // output from first
    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;     // the new stack can be empty after dequeue - hence last = null;
        N--;
    }

    /// ITERATOR ///
    // instantiate, and use the methods - each time it's used, it iterators by 1. Generally done using while loop: while i.hasNext(), i.next(), ...
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null; // returns true if current != null;
        }

        public void remove() {
        }

        public Item next() {
            Item item = current.item;
            current = current.next; // moves the current to the next node.
            return item;
        }
    }

    public static void main(String[] args) {
        Practice<String> q = new Practice<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) q.enqueue(item);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }
}
