import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class BruteCollinearPoints {
    private int numSeg = 0;
    private LineSegment[] segment = null;
    public BruteCollinearPoints(Point[] points) {
        checkDataSet(points);
        for (int a = 0; a < points.length;a++) {
            for (int b = a + 1; b < points.length; b++) {
                for (int c = b + 1; c < points.length; c++) {
                    for (int d = c + 1; c < points.length; d++) {

                    }
                }
            }
        }
    }

    private boolean checkDataSet(Point[] points) {
        Set<Point> set = new TreeSet<Point>();
        if (points == null) {
            throw new java.lang.NullPointerException("Null input");
        }
        for (int a = 0; a < points.length;a++) {
            if (points[a] == null) {
                throw new java.lang.NullPointerException("Null input");
            } else if (!set.add(points[a])) {
                throw new java.lang.IllegalArgumentException("Duplicate input");
            }
        }
        return false;
    }
    public int numberOfSegments() {
        return numSeg;
    }    // the number of line segments
    public LineSegment[] segments() {
        return segment;
    }            // the line segments
}
