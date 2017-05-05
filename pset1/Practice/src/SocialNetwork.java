/******************************************************************************
 *  Compilation:  javac SocialNetwork.java
 *  Execution:    java SocialNetwork input.txt
 *  Dependencies: WeightedQuickUnionUF.java
 *
 *  This program takes the name of a file as a command-line argument.
 *  From that file, it
 *
 *    - Reads the number of members N of the social network.
 *    - Creates an n-by-n grid of sites (intially all blocked)
 *    - Reads in a sequence of logfile where at time stamp m a pair of member (int i, int j) connect with each other.
 *
 *  After each connected is make, it will check if all members are connected
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.In;
public class SocialNetwork extends WeightedQuickUnionUF{
    private boolean connectedAllFlag; // Determine whether all members are connected.
    private int nMember; // number of members
    private int timeStamp; // time-stamp when all members are connected.
    private int m; // time-stamp counter

    public SocialNetwork(int N) {
        super(N);
        this.nMember = N;
    }

    /**
     *
     * @param m time-stamp when member p and member q become friends
     * @param p index of member p
     * @param q index of member q
     */
    @Override
    public void union(int p, int q)
    {
        m++;
        int i = root(p -1);
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
        if (sz[j] == nMember || sz[i] == nMember) {
            connectedAllFlag = true;
            this.timeStamp = m;
        }
    }

    /**
     *
     * @return true if all members are connected
     */
    public boolean isAllConnected()
    {
        return this.connectedAllFlag;
    }

    /**
     *
     * @return the time Stamp when all members are connected, return 0 if the social network is not connected
     */
    public int getTimeStamp()
    {
        return timeStamp;
    }


    public static void main(String args[])
    {

        In in = new In(args[0]);    // Input file
        int N = in.readInt();       // number of memmbers;
        SocialNetwork sn = new SocialNetwork(N);
        while (!sn.isAllConnected() && !in.isEmpty())
        {
            sn.union(in.readInt(), in.readInt());
        }
        if (sn.isAllConnected()) { System.out.println("The time stamp when all members is connected: " + sn.getTimeStamp()); }
        else { System.out.println("Not all members are connected."); }
    }
}
