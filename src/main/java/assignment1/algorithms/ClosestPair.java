package assignment1.algorithms;

import assignment1.metrics.Metrics;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    private final Metrics metrics;

    public ClosestPair(Metrics metrics) {
        this.metrics = metrics;
    }

    // ✅ Define the Point class here
    public static class Point {
        public final double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    // Main method to call
    public double findClosest(Point[] points) {
        Point[] sorted = points.clone();
        Arrays.sort(sorted, Comparator.comparingDouble(p -> p.x));
        return closestRecursive(sorted, 0, sorted.length - 1);
    }

    private double closestRecursive(Point[] pts, int left, int right) {
        if (right - left <= 3) return bruteForce(pts, left, right);

        int mid = (left + right) / 2;
        double leftDist = closestRecursive(pts, left, mid);
        double rightDist = closestRecursive(pts, mid + 1, right);
        double d = Math.min(leftDist, rightDist);

        double midX = pts[mid].x;
        Point[] strip = Arrays.stream(pts, left, right + 1)
                .filter(p -> Math.abs(p.x - midX) < d)
                .sorted(Comparator.comparingDouble(p -> p.y))
                .toArray(Point[]::new);

        return Math.min(d, stripClosest(strip, d));
    }

    private double bruteForce(Point[] pts, int left, int right) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double dx = pts[i].x - pts[j].x;
                double dy = pts[i].y - pts[j].y;
                metrics.incComparisons();  // ✅ fixed
                min = Math.min(min, Math.hypot(dx, dy));
            }
        }
        return min;
    }

    private double stripClosest(Point[] strip, double d) {
        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                double dx = strip[i].x - strip[j].x;
                double dy = strip[i].y - strip[j].y;
                metrics.incComparisons();  // ✅ fixed
                double dist = Math.hypot(dx, dy);
                if (dist < min) min = dist;
            }
        }
        return min;
    }
}
