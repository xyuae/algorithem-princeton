/*---------------------------------------------------------
 *  This is a program of Xiaojun YU constructed on 5/12/2016
 *  To implement stack and queue, linked list
 *  To understand generics and iterators
 *---------------------------------------------------------*/
//package ece651;
//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdStats;
import java.util.Iterator;
// Dequeue. A double-ended queue or deque is a generalization
// of a stack and a queue that supports adding and removing
// items from either the front or the back
// This is a double-linked list

public class Deque<Item> implements Iterable<Item> {
    // pointer for the header of linked list
    private Node head = null;
    private Node tail = null;
    private int size = 0;
    
    private class Node
    {
        Item item;
        Node last;
        Node next;
    }
    
    // construct an empty deque
    public Deque()
    {
        //pass
    }
    // is the deque empty
    public boolean isEmpty()
    {
        return head == null;
    }
    
    // return the number of items on the deque
    public int size()
    {
        return this.size;
    }
    // add the item to the front
    public void addFirst(Item item)
    {
        // Throw a exception if the client attempts to add a null item
        if (item == null)
        {
            throw new java.lang.NullPointerException();
        }
        // create the new head
        //System.out.println("So far");
        Node oldHead = this.head;
        this.head = new Node();
        head.item = item;
        // make the new head point to the old head
        head.next = oldHead;
        // if it is the first element to add
        if (size == 0)
        {
            // point the tail to this element
            tail = head;
        }
        else
        {
            // make the oldHead point to the new head
            // the oldHead is a null pointer when size == 0 
            oldHead.last = head;
        }
        // add 1 to size
        size ++;
    }
    // add the item to the end
    public void addLast(Item item)
    {
        // Throw a exception if the client attempts to add a null item
        if (item == null)
        {
            throw new java.lang.NullPointerException();
        }
        
        // create the new tail
        Node oldTail = this.tail;
        tail = new Node();
        tail.item = item;
        // make the new tail point to the old tail
        tail.last = oldTail;
        // if it is the first element to add
        if (size == 0)
        {
            // point the head to this element
            head = tail;
        }
        else
        {
            // make the oldTail point to the new tail
            oldTail.next = tail;
        }
        // increase size by 1
        size++;
    }
    // remove and return the item from the front
    public Item removeFirst()
    {
        // throw an exception if attempts to remove an item from an empty deque
        if (size == 0)
        {
            throw new java.util.NoSuchElementException();
        }
        Item item = head.item;
        // point the head to the next item
        head = head.next;
        // decrement size
        size --;
        // if this was the last item in the list
        if (size == 0 || head == null)
        {
            // point the tail to the head
            tail = head;
        }
        else
        {
            head.last = null;
        }
        return item;
    }
    // remove and return the item from the end
    public Item removeLast()
    {
        // throw an exception if attempts to remove an item from an empty deque
        if (size == 0)
        {
            throw new java.util.NoSuchElementException();
        }
        Item item = tail.item;
        // point the head to the next item
        tail = tail.last;
        // decrement size
        size --;
        // if this was the last item in the list
        if (size == 0 || tail == null)
        {
            // point the tail to the head
            head = tail;
        }
        else
        {
            tail.next = null;
        }
        return item;
    }
    // return an iterator over items in order  
    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }
    // return a List of Iterator
    private class ListIterator implements Iterator<Item>
    {
        private Node current = head;
        public boolean hasNext() {return current != null;}
        public void remove() { throw new UnsupportedOperationException();}
        public Item next()
        {
            // throw a Exception if the client calls the next() method 
            // in the iterator and there are no more items to return
            if (current.item == null)
            {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    // unit testing
    public static void main(String[] args)
    {// Construct a Deque
        Deque<Integer> list = new Deque<Integer>();
        list.addFirst(4);
        list.removeLast();
        //list.addLast(null);
        //list.removeLast();
        list.addLast(5);
        System.out.println(list.size());
        System.out.println("Expected 2");
        list.addFirst(3);
        list.addFirst(2);
        for (int i : list)
        {
            System.out.println(i);
        }
        System.out.println("Expected 2 3 4 5");
        System.out.println(list.removeFirst());
        System.out.println("Expected 2");
        System.out.println(list.removeLast());
        System.out.println("Expected 5");
        System.out.println(list.size());
        System.out.println("Expected 2");
        for (int i: list)
        {
            System.out.println(i);
        }
        System.out.println("3, 4");
    }
}


