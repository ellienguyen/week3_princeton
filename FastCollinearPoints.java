import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
public class FastCollinearPoints {
    private int numSeg = 0;
    private LineSegment[] segment = null;
    public FastCollinearPoints(Point[] points) {
        ArrayList<LineSegment> segmentList = new ArrayList<LineSegment>();
        checkDataSet(points);
        if (points.length >= 4) {
            Arrays.sort(points);
            for (int pointIndex = 0; pointIndex < points.length - 1; pointIndex++) {
                double[] slopeArray = new double[points.length - pointIndex - 1];
                Point origin = points[pointIndex];
                for (int j = pointIndex + 1; j < points.length; j++) {
                    slopeArray[j - pointIndex - 1] = origin.slopeTo(points[j]);
                }
                Arrays.sort(slopeArray);
                double prev;
                double cur;
                //5 points
                for (int itSlope = 1; itSlope < points.length - pointIndex - 1; itSlope++) {
                    cur = slopeArray[itSlope];
                    prev = slopeArray[itSlope - 1];
                    if (equals(prev, cur)) {
                        int countCol = itSlope - 1;
                        while (countCol < points.length - pointIndex - 1
                                && equals(slopeArray[countCol], prev)) {
                            countCol++;
                        }
                        segmentList.add(new LineSegment(origin, points[countCol - 1]));
                    }
                }
            }
            if (segmentList.size() != 0) {
                segment = segmentList.toArray(new LineSegment[numSeg]);
            }
        }
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