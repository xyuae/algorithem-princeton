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
// import java.lang.Math;
// Dequeue. A double-ended queue or deque is a generalization
// of a stack and a queue that supports adding and removing
// items from either the front or the back
// This is a double-linked list

public class TestDeque{
    public TestDeque()
    {
        //pass
    }
    public static void main(String[] args)
    {
        // Construct a Deque
        Deque<Integer> list = new Deque<Integer>();
        //list.addLast(null);
        //list.removeLast();
        list.addFirst(5);
        System.out.println("Resized into 1 current size 1 expected first 0");
        list.addFirst(3);
        System.out.println("Resized into 2 current size 2 expected first -1");
        list.addFirst(2);
        System.out.println("Resized into 4 current size 3 expected first -2");
        list.addFirst(3);
        System.out.println("Resized into 4 current size 4 expected first -1");
        list.addFirst(3);
        System.out.println("Resized into 8 current size 5 expected first -1");
        list.addFirst(3);
        System.out.println("Resized into 8 current size 6 expected first -2");
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.removeLast();
        list.removeLast();
        
        for (int i: list)
        {
            System.out.println(i);
        }
        System.out.println(" a long list");
        
    }
}

