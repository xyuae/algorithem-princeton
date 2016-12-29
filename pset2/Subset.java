/*  ---------------------------------------------------------
 *  This is a program of Xiaojun YU constructed on 8/12/2016
 *  To build client software that takes a command-line
 *  integer k; reads in a sequence of N strings form standard
 *  input using StdIn.readString(); and prints out k of them,
 *  uniformly at random. each item can be printed at most once.
 *  Assume 0 <= k <= n, where n is the number of string on output
 *    RandomziedQueue is implemented by an resizing array
 *  ---------------------------------------------------------*/
// import edu.princeton.cs.algs4.StdRandom;
// import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
// import java.lang.Math;


public class Subset {
   public static void main(String[] args) {
        int k = 0;
        if (args.length != 1)
        {
            System.out.println("Usage java Subset k");
            return;
        }
        else
        {
            try 
            {
                k = Integer.parseInt(args[0]);
            }
            catch (UnsupportedOperationException e) 
            {
                System.err.println("Usage java Subset k(integer)");
            }
            
        }
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            queue.enqueue(item);
        }
        for (String item : queue)
        {
            if (k == 0) return;
            StdOut.printf("%s\n", item);
            k--;
            
        }
    }
}

