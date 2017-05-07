import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class BruteCollinearPoints {
    private int numSeg = 0;
    private LineSegment[] segment = null;
    private Set<LineSegment> segmentSet = new TreeSet<LineSegment>();
    public BruteCollinearPoints(Point[] points) {
        checkDataSet(points);
        if (points.length >= 4) {
            for (int a = 0; a < points.length;a++) {
                for (int b = a + 1; b < points.length; b++) {
                    for (int c = b + 1; c < points.length; c++) {
                        for (int d = c + 1; c < points.length; d++) {
                            Point[] points1 = new Point[4];
                            points1[0] = points[a];
                            points1[1] = points[b];
                            points1[2] = points[c];
                            points1[3] = points[d];
                            if (checkCollinear(points1)) {
                                LineSegment col = new LineSegment(points1[0], points1[3]);
                                numSeg++;
                                segmentSet.add(col);
                            }
                        }
                    }
                }
            }
            segment = segmentSet.toArray(new LineSegment[numSeg]);
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

    private boolean checkCollinear(Point[] points) {
        Arrays.sort(points);
        double slope = points[0].slopeTo(points[1]);
        return slope == points[0].slopeTo(points[2]) && slope == points[0].slopeTo(points[3]);
    }

    public int numberOfSegments() {
        return numSeg;
    }    // the number of line segments
    public LineSegment[] segments() {
        return segment;
    }            // the line segments
}
