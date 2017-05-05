/*---------------------------------------------------------
 *  This is a program of Xiaojun YU constructed on 10/11/2016
 *  The purpose of this program is to estimate the percolation threshold
 *  Usage: java PercolationStats int int.
 *---------------------------------------------------------*/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats{
   private int n;
   private int trials;
   private Percolation siteMap;
   private double[] listTrialNum; //stores the probability of open sites over total sites to get percolation in each trial

    /**
     * perform trials independent experiments on an n-by-n grid
     * @param n
     * @param trials
     */
   public PercolationStats(int n, int trials)
   {
       if (n <= 0) throw new IndexOutOfBoundsException("input n smaller than 1");
       if (trials <= 0) throw new IndexOutOfBoundsException("input trials smaller than 1");
       this.n = n;
       this.trials = trials;
       this.listTrialNum = new double[trials];
       trial();
   }

    /**
     * perform trials independent experiments by the Percolation instance
     */
   private void trial()  // perform trials independent experiments by the Percolation instance
   {
       for (int i = 0; i < this.trials; i++)
       {
           this.siteMap = new Percolation(this.n);
           while (!siteMap.percolates())
           {
               int randX = StdRandom.uniform(1, n+1); //generate a random number from 1 to n
               int randY = StdRandom.uniform(1, n+1);
               siteMap.open(randX, randY);
           }
           this.listTrialNum[i] = (double)siteMap.numberOfOpenSites()/(n*n);  // if error, this might be chagne into newSeries[newSeries.length - 1] = newInt;
       }
   }
   public double mean()                          // sample mean of percolation threshold
   {
       return StdStats.mean(listTrialNum);
   }
   public double stddev()                        // sample standard deviation of percolation threshold
   {
       return StdStats.stddev(listTrialNum);
   }
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
       return mean() - 1.96*stddev()/Math.sqrt(n);
   }
   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
       return mean() + 1.96*stddev()/Math.sqrt(n);
   }

   public static void main(String[] args)    // test client (described below)
   {
        if (args.length != 2)
        {
            System.err.println("Usage: java PercolationStats int int.");
        }
    /** Test:  % java PercolationStats 200 100
    mean                    = 0.5929934999999997
    stddev                  = 0.00876990421552567
    95% confidence interval = 0.5912745987737567, 0.5947124012262428
    **/
        PercolationStats test = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.format("mean                    = %.16f%n", test.mean());
        System.out.format("stddev                  = %.16f%n", test.stddev());
        System.out.format("95%% confidence interval = %.16f, %.16f %n", test.confidenceLo(), test.confidenceHi());
   }
}