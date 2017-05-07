import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class FastCollinearPoints {
    private int numSeg = 0;
    private LineSegment[] segment = null;
    public FastCollinearPoints(Point[] points) {
        Set<LineSegment> segmentSet = new TreeSet<LineSegment>();
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
                for (int itSlope = 1; itSlope < points.length - pointIndex; itSlope++) {
                    cur = slopeArray[itSlope];
                    prev = slopeArray[itSlope - 1];
                    if (prev == cur) {
                        int countCol = itSlope - 1;
                        while (countCol < points.length - pointIndex - 1 && slopeArray[countCol] == prev) {
                            countCol++;
                        }
                        segmentSet.add(new LineSegment(origin, points[countCol - 1]));
                    }
                }
            }
            if (segmentSet.size() != 0) {
                segment = segmentSet.toArray(new LineSegment[numSeg]);
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
    }
}