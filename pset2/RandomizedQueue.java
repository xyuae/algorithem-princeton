/*  ---------------------------------------------------------
 *  This is a program of Xiaojun YU constructed on 6/12/2016
 *  To implement stack and queue using re-sizable array
 *  To understand generics and iterators
 *  ---------------------------------------------------------*/
// package ece651;
// import edu.princeton.cs.algs4.StdRandom;
// import edu.princeton.cs.algs4.StdStats;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
// import java.lang.Math;
// Dequeue. A double-ended queue or deque is a generalization
// of a stack and a queue that supports adding and removing
// items from either the front or the back
// This is a double-linked list

public class RandomizedQueue<Item> implements Iterable<Item> {
    // pointer for the header of linked list
    private Item[] s;
    private int last = 0;
    private int capacity;   // current capacity
    private int size = 0;
    // construct an empty randomized queue
    public RandomizedQueue()
    {
        // initate the array
        capacity = 2;
        s = (Item[]) new Object[2];
    }
    // is the queue empty
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    // return the number of items on the deque
    public int size()
    {
        return size;
    }
    // add the item
    public void enqueue(Item item)
    {
        // implement the addLast 
        // Throw a exception if the client attempts to add a null item
        if (item == null)
        {
            throw new java.lang.NullPointerException();
        }
        if (size == 0) 
        {
            s[0] = item;
        }
        else
        {
            if (capacity == size) resize(2 * capacity);
            // add item as the last element
            s[++last] = item;
        }
        size++;
    }
    
    // swap the value in cell 1 and 2
    private void swap(int index1, int index2)
    {
        if (index1 == index2) return;
        Item temp = s[index2];
        s[index2] = s[index1];
        s[index1] = temp;
    }
    
    // remove and return a random item
    public Item dequeue()
    {
        // throw a exception if the client attempts to sample or dequeue 
        // an item from an empty randomized queue
        if (size <= 0)
        {
            throw new NoSuchElementException();
        }
        if (size == 1)
        {
            Item temp = s[last];
            s[last] = null;
            size--;
            return temp;
        }
        // select the random cell with non-null value uniformly
        int i = StdRandom.uniform(size);
        // uniform(n) generates a random integer from 0 to n - 1
        Item item = s[i];
        swap(i, last);
        s[last] = null;
        last--;
        size--;
        // resize the array if the number valid cell is small
        // System.out.println("size: " + size + "; capacity: " + capacity);
        if (size == capacity / 4) resize(capacity * 1/ 2);
        return item;
    }
    // return (but do not remove) a random item
    public Item sample()                     
    // return an independent iterator over items in random order
    {
        // throw a exception if the client attempts to sample or dequeue 
        // an item from an empty randomized queue
        if (size <= 0)
        {
            throw new NoSuchElementException();
        }
        // uniform(n) generates a random integer from 1 to n - 1
        Item item = s[StdRandom.uniform(size)];
        return item;
    }

    // resize the array 
    private void resize(int capacityNew)
    {
        // System.out.println("length: "+ s.length);
        Item[] copy = (Item[]) new Object[capacityNew];
        // System.out.println(size);
        for (int i = 0; i < size; i++)
        {
            copy[i] = s[i];
        }
        s = copy;
        // set index of last to N-1 and first to 0
        last = size - 1;
        this.capacity = capacityNew;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }
    // return a List of Iterator
    private class ListIterator implements Iterator<Item>
    {
        private int current;
        private int lastC = last;
        private Item[] copy;
        private int sizeC = size;
        public ListIterator()  // maybe I need to input the size of s to constructor
        {
            current = 0;
            copy = (Item[]) new Object[sizeC];
            System.arraycopy(s, 0, copy, 0, sizeC);
            knuthShuffle();
            // System.out.println("the construcutor is called" + s[0] + copy[0]);
        }
        public boolean hasNext() { return current < sizeC; }
        public void remove() { throw new UnsupportedOperationException(); }
        // shrink copy form array and reduce the unnecsaary cell
        private void knuthShuffle()
        {
            for (int i = lastC; i >= 0; i--)
            {
                int j = StdRandom.uniform(i + 1);
                // swap the last element with a random element 
                swapC(i, j);
            }
        }
        
        private void swapC(int index1, int index2)
        {
            if (index1 == index2) return;
            Item temp = copy[index2];
            copy[index2] = copy[index1];
            copy[index1] = temp;
        }
        
        public Item next()
        {
            // throw a Exception if the client calls the next() method 
            // in the iterator and there are no more items to return
            try 
            {
                Item item = copy[current++];
                return item;
            }
            catch (java.util.NoSuchElementException e) 
            {
                System.err.println("No such element");
            }
            return null;
        }
    }

    // unit testing
    public static void main(String[] args)
    {
        // Construct a Deque
        RandomizedQueue<Integer> list = new RandomizedQueue<Integer>();
        // System.out.println("Test isEmpty() Expected true: " + list.isEmpty());
        list.enqueue(5);
        list.enqueue(6);

        list.dequeue();
        list.dequeue();
        list.enqueue(3);
        list.dequeue();
        list.enqueue(3);

        

        for (int i: list)
        {
            System.out.println(i);
        }
        System.out.println(" a long list");
    }
}


