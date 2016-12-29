/******************************************************************************
 *  Compilation:  javac BruteCollinearPoints.java
 *  Execution:    java BruteCollinearPoints
 *  Dependencies: Point.java LineSegement.java
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/
// import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
 
public class BruteCollinearPoints {
    private Point[] points;
    private int lineNumber;
    
    // finds all line segments containing 4 points
    public BruteCollinearPoints (Point[] points)
    {
        if (points.length <= 0)
        {
            throw new java.lang.NullPointerException();
        }
        this.points = new Point[points.length];
        this.points = points;
        
        // throw  a java.lang.IllegalArgumentException if the argument 
        // to the constructor contains a repeated point. 
        // sort the entire list of points
        BruteCollinearPoints.sort(this.points);
        // print the list of points after sorting by magnitude
        /* for (int i = 0; i < points.length; i++)
        {
            System.out.println("The point is : " + points[i]);
        }
        */
        
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }
    
    private static void sort(Point[] a) {
        int arrayLen = a.length;
        for (int i = arrayLen; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (a[j].compareTo(a[j + 1]) > 0) exch(a, j, j + 1);
                else if (a[j].compareTo(a[j + 1]) == 0) throw new java.lang.IllegalArgumentException();
            }
        }
    }
    
    private boolean inLine (Point[] fourPoints) {
        // return true if the four points are in line, otherwise return false
        // int arrayLen = fourPoints.length;
        Comparator<Point> c = fourPoints[0].slopeOrder();
        //construct the comparator with base point fourPoints[0]
        // StdOut.println(c.compare(fourPoints[1], fourPoints[2]));
        // StdOut.println(c.compare(fourPoints[2], fourPoints[3]));
        // StdOut.println("Test next");
        // System.out.println("Number is" + lineNumber);
        if (c.compare(fourPoints[1], fourPoints[2]) != 0) return false;
        if (c.compare(fourPoints[2], fourPoints[3]) != 0) return false;
        return true;
    }
    
    // the number of line segments
    public int numberOfSegments() {
        // To do
        return lineNumber;
    }
    
    // the line segments
    public LineSegment[] segments() {
        // To do
        lineNumber = 0;
        List<LineSegment> list = new ArrayList<LineSegment>();
        int leng = points.length;
        for (int i = 0; i < leng - 3; i++) {
            for (int j = i + 1; j < leng - 2; j++) {
                for (int k = j + 1; k < leng - 1; k++) {
                    for (int l = k + 1; l < leng; l++) {
                        // System.out.println(points[l]);
                        // Point[] n = new Point[] {points[i], points[j], points[k], points[l]};
                        if (inLine(new Point[] {points[i], points[j], points[k], points[l]}) == true) {
                            StdOut.println(i+ " " + j + " " + k + " "+ l);
                            lineNumber++;
                            LineSegment answer = new LineSegment(points[i], points[l]);
                            list.add(answer);
                        }
                    }
                }
            }
        }
        LineSegment[] result = new LineSegment[list.size()];
        int size = list.size();
        for (int i = 0; i < size; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
    
    /**
     * Unit tests the Point data type.
     */
    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
        }
        StdOut.println(collinear.numberOfSegments());
    }
    
    /* draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();
    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
    }
    */
}
    