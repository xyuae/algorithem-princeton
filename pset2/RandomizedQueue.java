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
    private int first = 0;
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
    // return index of input by modulus 
    private int index(int index)
    {
        return (index + capacity) % capacity;
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
        if(s[index(last)] == null) 
        {
            s[index(last)] = item;
        }
        else
        {
            if (capacity == last - first + 1) resize(2 * capacity);
            // add item as the last element
            s[index(++last)] = item;
        }
        size++;
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
        Item item;
        int i;
        // select the random cell with non-null value uniformly
        while(true)
        {
            i = index(StdRandom.uniform(last + 1));
            // uniform(n) generates a random integer from 0 to n - 1
            item = s[i];
            if (item != null) break;
        }
        s[i] = null;
        size --;
        // resize the array if the number valid cell is small
        // System.out.println("size: " + size + "; capacity: " + capacity);
        if (size == capacity / 4 && size > 0) resize(capacity * 3/ 4);
        while (last != first)
        {
            if (s[last] == null) last--;
            else break;
        }
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
        Item item;
        while(true)
        {
            // uniform(n) generates a random integer from 1 to n - 1
            item = s[index(StdRandom.uniform(size))];
            if (item != null) break;
        }
        return item;
    }

    // resize the array 
    private void resize(int capacity)
    {
        // System.out.println("length: "+ s.length);
        Item[] copy = (Item[]) new Object[capacity];
        int length = last - first + 1;  //length include the removed cell
        // System.out.println(size);
        for (int i = 0, j = 0; i < length; i++)
        {
            if (s[i + first] != null)
            {
                copy[j] = s[i + first];
                j++;
            }
        }
        s = copy;
        // set index of last to N-1 and first to 0
        last = size - 1;
        first = 0;
        this.capacity = capacity;
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
        private int first_c = first;
        private int last_c = last;
        private int capacity_c = capacity;
        private Item[] copy;
        private int size_c = size;
        public ListIterator()  // maybe I need to input the size of s to constructor
        {
            copy = (Item[]) new Object[s.length];
            System.arraycopy( s, 0, copy, 0, s.length);
            // System.out.println("the construcutor is called" + s[0] + copy[0]);
        }
        public boolean hasNext() { return size_c != 0;}
        public void remove() { throw new UnsupportedOperationException();}
        // shrink copy form array and reduce the unnecsaary cell
        private void shrink_copy(int capacity)
        {
            //System.out.println("The shirnk_copy is called");
            Item[] new_copy = (Item[]) new Object[capacity];
            int length = last_c - first_c + 1;
            for (int i = 0, j = 0; i < length; i++)
            {
                if (copy[i + first] != null)
                {
                    new_copy[j] = copy[i + first_c];
                    j++;
                }
            }
            copy = new_copy;
            last_c = size_c - 1;
            first_c = 0;
            capacity_c = capacity;
        }
        
        public Item next()
        {
            //System.out.println("copy: " + this.copy[0] + " ");
            if (size_c == capacity_c / 2 && size_c > 0) shrink_copy(capacity_c / 2);
            // throw a Exception if the client calls the next() method 
            // in the iterator and there are no more items to return
            int index;
            while (true)
            {
                // System.out.println("last_c:" + last_c);
                index = StdRandom.uniform(last_c + 1); 
                // System.out.println("index: " + index);
                // System.out.println("copy: " + copy[0] + " ");
                if (copy[index] != null)
                {
                    Item item = copy[index];
                    copy[index] = null;
                    size_c --;
                    // resize the array if the number valid cell is small
                    // System.out.println("size_c: " + size_c + "; capacity_c: " + capacity_c);
                    while (last_c != first_c)
                    {
                        if (copy[last_c] == null) last_c--;
                        else break;
                    }
                    return item;
                }
            }
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
        list.enqueue(7);

        for (int i: list)
        {
            System.out.println(i);
        }
        System.out.println(" a long list");
    }
}


