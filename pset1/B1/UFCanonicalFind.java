import edu.princeton.cs.algs4.In;

import java.util.Random;

/******************************************************************************
 *  Compilation:  javac UFCanonicalFind.java
 *  Execution:    java UFCanonicalFind
 *  Dependencies: WeightedQuickUnionUF.java
 *
 *  This program doesn't take any command-line argument.
 *
 ******************************************************************************/

public class UFCanonicalFind extends WeightedQuickUnionUF{
    private int[] largeEle;

    public UFCanonicalFind(int N)
    {
        super(N);
        largeEle = new int[N];
        for (int i = 0; i < N; i++)
        {
            largeEle[i] = i + 1;
        }
    }

    /**
     *
     * @param p index of member p
     * @param q index of member q
     */
    @Override
    public void union(int p, int q)
    {
        int i = root(p - 1);
        int j = root(q - 1);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
        }
        if (largeEle[i] < largeEle[j]) {
            largeEle[i] = largeEle[j];
        }
        else {
            largeEle[j] = largeEle[i];
        }
    }

    /**
     *
     * @param x
     * @return the largest component in the connected component containing x
     */
    public int find(int x)
    {
        int i = x - 1;
        return largeEle[i];
    }

    public static void main(String args[])
    {
        In in = new In(args[0]);    // Input file
        int N = in.readInt();       // number of memmbers;
        UFCanonicalFind uf = new UFCanonicalFind(N);
        while (!in.isEmpty())
        {
            uf.union(in.readInt(), in.readInt());
        }
        Random rd = new Random();
        int i = rd.nextInt(N - 1) + 1;
        try{
            System.out.println("The largest component of integer " + i + " is: " + uf.find(i));
        } catch (Exception e) {
            System.out.println("The error occurs with inedx " + i);
        }
    }
}