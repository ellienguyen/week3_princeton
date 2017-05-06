import java.util.Arrays;

public class BruteCollinearPoints {
    private int numSeg = 0;
    private LineSegment[] segment = null;
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.NullPointerException("Null input");
        }
        for (int b = a + 1; b < points.length; b++) {
            for (int c = b + 1; c < points.length; c++) {
                for (int d = c + 1; c < points.length; d++) {

                }
            }
        }
        for (int a = 0; a < points.length;a++) {
            if (points[a] == null) {
                throw new java.lang.NullPointerException("Null input");
            } else {
        }

    }
    public int numberOfSegments() {
        return numSeg;
    }    // the number of line segments
    public LineSegment[] segments() {
        return segment;
    }            // the line segments
}
