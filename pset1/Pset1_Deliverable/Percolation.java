/*---------------------------------------------------------
 *  This is a program of Xiaojun YU constructed on 10/11/2016
 *  The purpose of this program is to construct the data structure to solve the perculate problem
 *  using union-find algorithm
 *---------------------------------------------------------*/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final int n;   // n by n grid
    private int numberOfOpenSites; // numberOfOpenSites
    private boolean isPercolate; // true if site percolate, false otherwise
    private boolean [] map; // array to determine if a site is open
    private WeightedQuickUnionUF uf; // WeightedQuickUnionUF to track if two sites is connected

    /**
     * Constructor
     * Create n-by-n grid, with all sites blocked
     * @param n side length of the grid
     */
    public Percolation(int n)
    {
        // throw a java.lang.IllegalArgumentException if n <= 0
        if (n <= 0) throw new IllegalArgumentException();
        uf = new WeightedQuickUnionUF(n*n+1); // initialize n*n + 1 array size, the extra 1 node represent the virtual-top site
        map = new boolean[n * n]; // initialize n*n array size from 0 to n * n -1
        this.n = n;
    }

    /**
     *
     * @param row
     * @param col
     * @return index of the site[row][col] corresponding index in WeightedQuickUnionFind uf
     */
    private int map(int row, int col) // map the row and col into index of the list in WeightedQuickUnionUF
    {
        if (row < 1 || row > n) throw new IndexOutOfBoundsException("row index out of bounds");
        if (col < 1 || col > n) throw new IndexOutOfBoundsException("column index out of bounds");
        int i = row -1;
        int j = col -1;
        int index = i * n + j;
        return index;
    }

    /**
     * open site(row, col) if it is not open already
     * @param row row index
     * @param col column index
     */
    public void open(final int row, final int col)
    {
        if (row < 1 || row > n) throw new IndexOutOfBoundsException("row index out of bounds");
        if (col < 1 || col > n) throw new IndexOutOfBoundsException("column index out of bounds");
        if (isOpen(row, col)){
            return;
        }
        else{
            this.numberOfOpenSites++;

            map[map(row, col)] = true;
            // union the virtual top node and the current node if row = 1
            if (row == 1) uf.union(n*n, map(row, col));
            int [] listX ={
                    row-1, row, row+1,row
            };
            int [] listY = {
                    col, col+1,col,col-1
            };

            for (int m = 0; m < 4; m++)
            {
                // check if the row and column is within assumption, especially for boundary nodes.
                if (listX[m] > 0 && listX[m] <=n && listY[m] >0 && listY[m] <=n)
                {
                    // check whether the neighbour site is open
                    if (isOpen(listX[m], listY[m]))
                    {
                        uf.union(map(listX[m],listY[m]),map(row,col));
                        // check whether the neighbour site is in the bottom row
                        if (listX[m] == n && isPercolate == false){
                            if (isFull(row, col)) this.isPercolate = true;
                        }
                    }
                }
            }
            // when the opening site is at the bottom row
            if (row == n && isPercolate == false){
                if (isFull(row, col)) this.isPercolate = true;
            }
        }
    }

    /**
     * Determine if site(row, col) is open
     * @param row row indices
     * @param col column indices
     * @return boolean true if site(row, col) is open, false otherwise
     */
    public boolean isOpen(int row, int col) // is site (row, col) open?
    {
        if (row < 1 || row > n) throw new IndexOutOfBoundsException("row index out of bounds");
        if (col < 1 || col > n) throw new IndexOutOfBoundsException("column index out of bounds");
        return map[map(row, col)];
    }

    /**
     *
     * @return the numberOfOPenSites
     */
    public int numberOfOpenSites()
    {
        return this.numberOfOpenSites;
    }

    /**
     * Determine if site(row, col) is connect with the top row
     * @param row row indices
     * @param col column indices
     * @return boolean true if site(row, col) connect with the top row, false otherwise
     */
    public boolean isFull(int row, int col)
    {
        if (row < 1 || row > n) throw new IndexOutOfBoundsException("row index out of bounds");
        if (col < 1 || col > n) throw new IndexOutOfBoundsException("column index out of bounds");
        return uf.connected(n * n, map(row, col));
    }



    /**
     * does the system percolate?
     * @return true if the system percolate, false otherwise
     */
    public boolean percolates()
    {
        if(!isPercolate){
            int row = n;
            for (int col = 1; col <= n; col++)
            {
                if (uf.connected(n*n, map(row, col))) isPercolate = true; 
            }
        }
        return this.isPercolate;
    }

    public static void main(String[] args) {
        // Construct a new siteMap with every site blocked
        Percolation siteMap = new Percolation(3);
        siteMap.open(1,1);
        siteMap.open(2,2);
        siteMap.open(2,1);
        System.out.println(siteMap.isOpen(1,1));
        System.out.println(siteMap.isOpen(2,1));
        System.out.println(siteMap.isOpen(1,1));
        System.out.println(siteMap.percolates()); // tests if the siteMap percolates
    }
}

