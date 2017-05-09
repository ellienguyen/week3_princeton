
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;
import java.util.Set;

public class FastCollinearPoints {
    private int numSeg = 0;
    private LineSegment[] segment = null;
    public FastCollinearPoints(Point[] points) {
        ArrayList<LineSegment> segmentList = new ArrayList<LineSegment>();
        checkDataSet(points);
        if (points.length >= 4) {
            Arrays.sort(points);
            for (int pointIndex = 0; pointIndex <= points.length - 4; pointIndex++) {
                ArrayList<Point> slopeArray = new ArrayList<Point>();
                Point origin = points[pointIndex];
                for (int j = pointIndex + 1; j < points.length; j++) {
                    slopeArray.add(points[j]);
                }
                Collections.sort(slopeArray, origin.slopeOrder());
                for (int itSlope = 2; itSlope < slopeArray.size(); itSlope++) {
                    double one = origin.slopeTo(slopeArray.get(itSlope));
                    double two = origin.slopeTo(slopeArray.get(itSlope - 1));
                    double three = origin.slopeTo(slopeArray.get(itSlope - 2));
                    if (equals(one, two) && equals(two, three)) {
                        int itSlope2;
                        for (itSlope2 = itSlope; itSlope2 < slopeArray.size(); itSlope2++) {
                            if (!equals(origin.slopeTo(slopeArray.get(itSlope2)), one)) {
                                segmentList.add(new LineSegment(origin, slopeArray.get(itSlope2 - 1)));
                                itSlope = itSlope2;
                                break;
                            }
                        }
                        if (itSlope2 == slopeArray.size()) {
                            segmentList.add(new LineSegment(origin, slopeArray.get(itSlope2 - 1)));
                            break;
                        }
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
        if (b == Double.POSITIVE_INFINITY || b == Double.NEGATIVE_INFINITY) {
            return false;
        }
        return (Math.abs(a - b) < 0.0001);
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
        return new LineSegment[0];
    }


}