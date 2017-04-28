/*---------------------------------------------------------
 *  This is a program of Xiaojun YU constructed on 10/11/2016
 *  The purpose of this program is to construct the datastrucutre to solve the perculate problem using union-find algorithm
 *---------------------------------------------------------*/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation{
    private int n;
    private int [][] sitemap;
    private WeightedQuickUnionUF uf;
    public Percolation(int n)      // create n-by-n grid, with all sites blocked
    {
        //super(n*n);
        //throw a java.lang.IllegalArgumentException if n ≤ 0
        if (n <= 0)
        {
            IllegalArgumentException e = new IllegalArgumentException("n <= 0");
            throw e;
        }
        uf = new WeightedQuickUnionUF(n*n+2); //initialze n+2 array size, the extra 2 node represent the virtual-top and virtual-bottom node
        sitemap = new int[n][n];
        for (int i = 0; i<n; i++){
            for (int j = 0; j<n; j++){
                sitemap[i][j] = 0;
            }    
        }
        this.n = n; 
    }
    private int map(int row, int col) // map the row and col into index of the list in WeightedQuickUnionUF
    {
        int i = row -1;
        int j = col -1;
        int index = i * n + j;
        return index;
    }
    public void open(int row, int col) //open site(row,col) if it is not open already
    {
        if (isOpen(row, col)){
            return;
        }
        else{
            try{
                int i = row -1;
                int j = col -1;
                sitemap[i][j] = 1; //open the site(row, col)
                // union the vertiual top node and the current node if row = 1
                if (row == 1) uf.union(n*n, map(row, col));
                // union the vertiual bottom node and the current node if row = n
                if (row == n) uf.union(n*n+1, map(row, col));
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
                        // check whetehr the neighbour site is open
                        if (isOpen(listX[m], listY[m]))
                        {
                            uf.union(map(listX[m],listY[m]),map(row,col));
                        }
                    }
                }
            }
            catch (IndexOutOfBoundsException e){
                System.err.println("IndexOutOfBoundsException in open: " +e.getMessage());
            }
        }
    }
    
    public boolean isOpen(int row, int col) // is site (row, col) open?
    {
        boolean isOpen = false;
        try{
            int i = row -1;
            int j = col -1;
            if (sitemap[i][j] == 1){
                isOpen = true;
            }
        }
        catch (IndexOutOfBoundsException e){
            System.err.println("IndexOutOfBoundsException in isOpen: " +e.getMessage());
        }    
        return isOpen;
    }
    
    public boolean isFull(int row, int col) // is site (row, col) full?
    {
        // This is implemented as the opposite of isOpen
        boolean isFull = false;
        try{
            int i = row -1;
            int j = col -1;
            if (sitemap[i][j] == 0){
                isFull = true;
            }
        }
        catch (IndexOutOfBoundsException e){
            System.err.println("IndexOutOfBoundsException: " +e.getMessage());
        }
        return isFull;
    }
    public boolean percolates() //does the system percolate?
    {
        return uf.connected(n*n, n*n+1);
    }

    public static void main(String[] args) { 
        // Construct a new siteMap with every site blocked
        Percolation siteMap = new Percolation(2);
        siteMap.open(1,1);
        siteMap.open(2,2);
        System.out.println(siteMap.isOpen(1,1));
        System.out.println(siteMap.isOpen(2,1));
        //System.out.println(siteMap.isOpen(1,3));
        System.out.println(siteMap.percolates()); // tests if the siteMap percolates
    }

}

