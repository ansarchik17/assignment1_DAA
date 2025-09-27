package assignment1;

import assignment1.algorithms.ClosestPair;
import assignment1.algorithms.ClosestPair.Point;
import assignment1.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    private double bruteForce(Point[] points) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dx = points[i].x - points[j].x;
                double dy = points[i].y - points[j].y;
                min = Math.min(min, Math.hypot(dx, dy));
            }
        }
        return min;
    }

    @Test
    void testCorrectnessSmallN() {
        Random rnd = new Random(12345);
        for (int n = 10; n <= 200; n += 50) {
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new Point(rnd.nextDouble() * 10000, rnd.nextDouble() * 10000);
            }

            Metrics metrics = new Metrics();
            ClosestPair cp = new ClosestPair(metrics);

            double fast = cp.findClosest(points.clone());  // Works now
            double brute = bruteForce(points);

            assertEquals(brute, fast, 1e-6, "ClosestPair mismatch for n=" + n);
        }
    }
}
