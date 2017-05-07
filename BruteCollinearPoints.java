import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class BruteCollinearPoints {
    private int numSeg = 0;
    private LineSegment[] segment = null;
    public BruteCollinearPoints(Point[] points) {
        ArrayList<LineSegment> segmentList = new ArrayList<LineSegment>();
        checkDataSet(points);
        if (points.length >= 4) {
            for (int a = 0; a < points.length; a++) {
                for (int b = a + 1; b < points.length; b++) {
                    for (int c = b + 1; c < points.length; c++) {
                        for (int d = c + 1; d < points.length; d++) {
                            Point[] points1 = new Point[4];
                            points1[0] = points[a];
                            points1[1] = points[b];
                            points1[2] = points[c];
                            points1[3] = points[d];
                            if (checkCollinear(points1)) {
                                LineSegment col = new LineSegment(points1[0], points1[3]);
                                numSeg++;
                                segmentList.add(col);
                            }
                        }
                    }
                }
            }
            if (segmentList.size() != 0) {
                segment = segmentList.toArray(new LineSegment[numSeg]);
            }
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

    private boolean equals(double a, double b) {
        if (a == Double.POSITIVE_INFINITY) {
            return b == Double.POSITIVE_INFINITY;
        }
        if (a == Double.NEGATIVE_INFINITY) {
            return b == Double.NEGATIVE_INFINITY;
        }
        return b == Double.POSITIVE_INFINITY || b == Double.NEGATIVE_INFINITY &&
                (Math.abs(a - b) < 0.0001);
    }

    public int numberOfSegments() {
        return numSeg;
    }    // the number of line segments
    public LineSegment[] segments() {
        if (segment != null) {
            return Arrays.copyOf(segment, segment.length);
        }
        return null;
    }
}
