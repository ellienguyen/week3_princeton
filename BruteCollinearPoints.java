
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private int numSeg = 0;
    private LineSegment[] segment = null;
    private ArrayList<LineSegment> segmentList = new ArrayList<LineSegment>();
    private Point startLE = null;
    private Point endLE = null;
    public int countCombination = 0;
    public BruteCollinearPoints(Point[] points) {
        checkDataSet(points);
        if (points.length >= 4) {
            Arrays.sort(points);
            Point[] pointSet = new Point[4];
            combination(points, pointSet, 0, points.length - 1, 0, 4);
            if (segmentList.size() != 0) {
                segment = segmentList.toArray(new LineSegment[numSeg]);
            }
        }
    }

    private void combination(Point[] points, Point[] set, int start, int end, int index, int r) {
        if (index == r) {
            countCombination++;
            if (checkCollinear(set)) {
                LineSegment col = new LineSegment(set[0], set[3]);
                numSeg++;
                segmentList.add(col);
//                if (startLE == null || set[0] != startLE && set[3] != endLE && !checkDuplicate
//                        (set)) {
//                    startLE = set[0];
//                    endLE = set[1];
//                    LineSegment col = new LineSegment(set[0], set[3]);
//                    numSeg++;
//                    segmentList.add(col);
//                }
            }
            return;
        }
        for (int i = start; i <= end && end-i+1 >= r-index; i++) {
            set[index] = points[i];
            combination(points, set, i+1, end, index + 1, r);
        }
    }

    private boolean checkDataSet(Point[] points) {
        Set<Point> set = new TreeSet<Point>();
        if (points == null) {
            throw new java.lang.NullPointerException("Null input");
        }
        for (int a = 0; a < points.length; a++) {
            if (points[a] == null) {
                throw new java.lang.NullPointerException("Null input");
            } else if (!set.add(points[a])) {
                throw new java.lang.IllegalArgumentException("Duplicate input");
            }
        }
        return false;
    }

    private boolean checkCollinear(Point[] points) {
        Arrays.sort(points);
        double slope = points[0].slopeTo(points[1]);
        return equals(slope, points[0].slopeTo(points[2])) && equals(slope, points[0].slopeTo(points[3]));
    }

//    private boolean checkDuplicate(Point[] points) {
//        Set<Point> set = new TreeSet<Point>();
//        for (int a = 0; a < points.length; a++) {
//            if (!set.add(points[a])) {
//                return true;
//            }
//        }
//        return false;
//    }

    private boolean equals(double a, double b) {
        if (a == Double.POSITIVE_INFINITY) {
            return b == Double.POSITIVE_INFINITY;
        }
        if (a == Double.NEGATIVE_INFINITY) {
            return b == Double.NEGATIVE_INFINITY;
        }
        if (b == Double.POSITIVE_INFINITY || b == Double.NEGATIVE_INFINITY) {
            return false;
        }
        return (Math.abs(a - b) < 0.0001);
    }

    public int numberOfSegments() {
        return numSeg;
    }    // the number of line segments
    public LineSegment[] segments() {
        if (segment != null) {
            return Arrays.copyOf(segment, segment.length);
        }
        return new LineSegment[0];
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In("/Users/admin/Google Drive/College/Week3/src/horizontal25.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
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
            StdOut.println(collinear.countCombination);
            segment.draw();
        }
        StdDraw.show();
    }

}
