/**
 * Created by Xiaojun YU on 2017-02-18.
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public MaxPQ(int capacity)
    {
        pq = (Key[]) new Comparable[capacity + 1];
        N = capacity;
    } // fixed capacity
    public boolean isEmpty(){
        return N == 0;
    }
    public void insert(Key key){}
    public Key delMax(){return null;}

    private boolean less(int i , int j)
    {
        return pq[i].compareTo(pq[j]) < 0;
    }
    private void exch(int i, int j)
    {
        Key t = pq[i]; pq[i] = pq[j]; pq[j] = t;
    }
}
