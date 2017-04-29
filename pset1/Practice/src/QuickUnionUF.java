/**
 * Created by Xiaojun YU on 2017-04-28.
 */
public class QuickUnionUF {
    private int[] id;

    public QuickUnionUF(int N) // set id of each object to itself
    {
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
    }

    private int root(int i) // chase parent pointers unitl reach root (depth of i array accesses)
    {
        while (i != id[i]) i = id[i];
        return i;
    }

    // check if p and q have same root (depth of p and q array accesses)
    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

    // change root of p to point to root of q (depth of p and q array accesses)
    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
}
