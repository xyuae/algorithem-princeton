/**
 * Created by Xiaojun YU on 2017-04-28.
 */
public class WeightedQuickUnionUF {
    protected int[] id;
    protected int[] sz;

    public WeightedQuickUnionUF(int N) // set id of each object to itself
    {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    protected int root(int i) // chase parent pointers until reach root (depth of i array accesses)
    {
        while (i != id[i]) {
            // make every other node in path point to its grandparent.
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    // check if p and q have same root (depth of p and q array accesses)
    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

    //

    // change root of p to point to root of q (depth of p and q array accesses)
    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else { id[j] = i; sz[i] += sz[j]; }
    }
}

