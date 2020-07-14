/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
    - similar to stack or queue, but item removed at random among items
    - use array implementation
    - need to implement resizing array - but skip for now.
        * turns out we need to resize to provide extra capacity to array at all times (SOLUTION).
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] s;
    private int N;

    // construct an empty randomized queue
    public RandomizedQueue(int capacity) {
        s = (Item[]) new Object[capacity];  // "cheated" by using capacity
        N = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        s[N++] = item;  // adds at N, then increment N by 1.
    }

    // remove and return a random item
    // null space will still be there, but item will be null - array can be resized
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        int ind = StdRandom.uniform(N - 1);
        Item item = s[ind];
        s[ind] = null;  // prevent loitering
        N--;
        return item;
        // or copy the old array - removed element to a new array? But that would require for-loop (or use while - iterator?).
    }

    // SOLUTION (REF) - pushed the random item to the end of the array - but what about the old end element? There always is a capacity since we are resizing the array.
    // public Item dequeue() {
    //     if(isEmpty()) throw new NoSuchElementException("Stack underflow");
    //     int ind = StdRandom.uniform(N-1);
    //     Item item = s[ind];
    //     s[ind] = s[N-1];
    //     s[N-1] = null;
    //     N--;
    //     return item;
    // }

    // return a random item (but do not remove it)
    public Item sample() {
        int ind = StdRandom.uniform(N - 1);
        return s[ind];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueArrayIterator();
    }

    private class QueueArrayIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return s[i++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}
