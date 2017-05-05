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

public class PredecentWithDelete extends WeightedQuickUnionUF{
    // private int[] largeEle;
    private int N;

    public PredecentWithDelete(int N)
    {
        super(N);
        this.N = N;
    }

    /**
     *
     * @param p index of member p
     */
    public void union(int p){
        if (p <= 1 || p >= N) return;
        int i = p - 1;
        int rootI = root(i);
        if (rootI != i) return;
        int j = rootI - 1;
        if (j < 0 || j >= N) return;
        int rootJ = root(j);
        if (rootI == rootJ) return;
        id[rootI] = rootJ;
    }

    /**
     *
     * @param x
     * @return the largest component in the connected component containing x
     */
    public int find(int x)
    {
        int i = x - 1;
        return root(i) - 1;
    }

    public static void main(String args[]) throws Exception {

        In in = new In(args[0]);    // Input file
        int N = in.readInt();       // number of memmbers;
        PredecentWithDelete uf = new PredecentWithDelete(N);
        while (!in.isEmpty())
        {
            uf.union(in.readInt());
        }
        Random rd = new Random();
        int i = rd.nextInt(N) + 1;
        System.out.println("The successor of integer " + i + " is: " + uf.find(i));
    }
}