/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        assert x >= 0 && y >= 0;
        assert x <= 32767 && y <= 32767;
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        // return Double.POSITIVE_INFINITY if the line segment is vertical;
        if (that.x == this.x) {
            if (that.y != this.y) {
                return Double.POSITIVE_INFINITY;
            }
        // return Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal
            else {
                return Double.NEGATIVE_INFINITY;
            }
        }
        double slope = (that.y - this.y)/(that.x - this.x);
        return slope;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (this.y > that.y) {
            return 1;
        }
        else if (this.y == that.y) {
            if (this.x > that.x) {
                return 1;
            }
            else if (this.x == that.x) {
                return 0;
            }
            // the case y0 = y1, x0 < x1
            else {
                return -1;
            }
        }
        // the case y0 < y1
        else return -1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new slopeCompare();
    }
    
    // exhange items in index i and j in array a
    
    private class slopeCompare implements Comparator<Point>
    {
        Point n0 = new Point(x, y);
        public int compare(Point n1, Point n2)
        {
            if (n0.slopeTo(n1) < (n0.slopeTo(n2))) return -1;
            else if (n0.slopeTo(n1) > (n0.slopeTo(n2))) return 1;
            // if (Point.this.slopeTo(n1) < (Point.this.slopeTo(n2))) return -1;
            // else if (Point.this.slopeTo(n1) > (Point.this.slopeTo(n2))) return 1;
            else return 0;
        }
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        
        Point n1 = new Point(1, 3);
        Point n2 = new Point(2, 5);
        System.out.println("Test slopeTo(that): ");
        System.out.println("The slope is " + n1.slopeTo(n2) + " The expected answer is 2");
        if (n1.slopeTo(n2) == 2)
        {
            System.out.println("Success");
        }
        else System.out.println("Failure");
        System.out.println("-----------------");
        
        System.out.println("Test compareTo(Point that): ");
        if (n1.compareTo(n2) == -1)
        {
            System.out.println("Success");
        }
        else System.out.println("Failure");
        System.out.println("-----------------");
        if (n1.compareTo(n2) < 0)
        {
            System.out.println(n1 + " is smaller than " + n2);
        }
        else
        {
            System.out.println(n1 + " is larger than " + n2);
        }
    }
}