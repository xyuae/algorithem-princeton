/*  ---------------------------------------------------------
 *  This is a program of Xiaojun YU constructed on 6/12/2016
 *  To implement stack and queue using re-sizable array
 *  To understand generics and iterators
 *  ---------------------------------------------------------*/
// package ece651;
// import edu.princeton.cs.algs4.StdRandom;
// import edu.princeton.cs.algs4.StdStats;
import java.util.Iterator;
// import java.lang.Math;
// Dequeue. A double-ended queue or deque is a generalization
// of a stack and a queue that supports adding and removing
// items from either the front or the back
// This is a double-linked list

public class Deque<Item> implements Iterable<Item> {
    // pointer for the header of linked list
    private Item[] s;
    private int first = 0;
    private int last = 0;
    private int capacity = 0;   // current capacity
    
    
    
    // construct an empty deque
    public Deque()
    {
        // initate the array
        capacity = 2;
        s = (Item[]) new Object[2];
    }
    // is the deque empty
    public boolean isEmpty()
    {
        return s[index(first)] == null;
    }
    // return index of input by modulus 
    private int index(int index)
    {
        return (index + 2* capacity) % capacity;
    }
    // return the number of items on the deque
    public int size()
    {   
        if (s[index(first)] == null || s[index(last)] == null) return 0;
        return last - first + 1;
    }
    // add the item to the front
    public void addFirst(Item item)
    {
        // Throw a exception if the client attempts to add a null item
        if (item == null)
        {
            throw new java.lang.NullPointerException();
        }
        if(s[index(first)] == null) 
        {
            s[index(first)] = item;
            //System.out.println("addFirst first" + first);
            //System.out.println("addFirst last" + last);
            //System.out.println("addFirst capacity" + capacity);
        }
        else
        {
            if (capacity == last - first + 1) 
            {
                //System.out.println("length: "+s.length);
                resize(2 * s.length);
            }
            s[index(--first)] = item;
        }
    }
    // add the item to the end
    public void addLast(Item item)
    {
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
            if (capacity == last - first + 1) resize(2 * s.length);
            // add item as the last element
            s[index(++last)] = item;
        }
    }
    
    // resize the array 
    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        int size = last - first + 1;
        for (int i = 0; i < size; i++)
            copy[i] = s[index(i + first)];
        s = copy;
        // set index of last to N-1 and first to 0
        last = (last - first) % this.capacity;
        first = 0;
        this.capacity = capacity;
    }
    // remove and return the item from the front
    public Item removeFirst()
    {
        // throw an exception if attempts to remove an item from an empty deque
        if (s[index(first)] == null)
        {
            throw new java.util.NoSuchElementException();
        }
        Item item = s[index(first)];
        // if this is the last elment in array
        if (index(first) == index(last))
        {
            s[index(first)] = null;
            return item;
        }
        else
        {
            s[index(first++)] = null;
            if (last - first + 1 == s.length/4 && s[index(first)] != null) resize(s.length/2);
            return item;
        }
    }
    // remove and return the item from the end
    public Item removeLast()
    {
        // throw an exception if attempts to remove an item from an empty deque
        if (s[index(last)] == null)
        {
            throw new java.util.NoSuchElementException();
        }
        Item item = s[index(last)];
        if (index(first) == index(last))
        {
            s[index(last)] = null;
            return item;
        }
        else
        {
            s[index(last--)] = null;
            if (last - first + 1 == s.length/4 && s[index(last)] != null) resize(s.length/2);
            return item;
        }
    }
    // return an iterator over items in order  
    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }
    // return a List of Iterator
    private class ListIterator implements Iterator<Item>
    {
        private int current = first;
        public boolean hasNext() {return current <= last && s[index(current)] != null;}
        public void remove() { throw new UnsupportedOperationException();}
        public Item next()
        {
            // throw a Exception if the client calls the next() method 
            // in the iterator and there are no more items to return
            if (s[index(current)] == null)
            {
                throw new java.util.NoSuchElementException();
            }
            Item item = s[index(current++)];
            return item;
        }
    }
    // unit testing
    public static void main(String[] args)
    {
        // Construct a Deque
        Deque<Integer> list = new Deque<Integer>();
        list.addFirst(5);
        list.removeFirst();
        list.addLast(2);
        list.removeLast();
        list.addFirst(3);
        list.addFirst(3);
        list.addFirst(3);
        list.addFirst(3);
        list.removeLast();
        list.removeFirst();
        System.out.println(list.size());
        for (int i: list)
        {
            System.out.println(i);
        }
        System.out.println(" a long list");
        
    }
}


