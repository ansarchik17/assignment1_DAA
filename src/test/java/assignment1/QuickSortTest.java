package assignment1;

import assignment1.algorithms.QuickSort;
import assignment1.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @Test
    void testCorrectnessRandom() {
        Random rnd = new Random();
        for (int n = 10; n <= 500; n += 50) {
            int[] arr = rnd.ints(n, 0, 10000).toArray();
            int[] expected = arr.clone();
            Arrays.sort(expected);

            Metrics metrics = new Metrics();
            QuickSort quickSort = new QuickSort(metrics);
            quickSort.sort(arr);

            assertArrayEquals(expected, arr, "QuickSort failed for n=" + n);
        }
    }

    @Test
    void testRecursionDepthBound() {
        int n = 1024;
        int[] arr = new Random().ints(n, 0, 10000).toArray();

        Metrics metrics = new Metrics();
        QuickSort quickSort = new QuickSort(metrics);
        quickSort.sort(arr);

        int logn = (int) (Math.log(n) / Math.log(2));
        assertTrue(metrics.maxDepth <= 2 * logn + 5,
                "QuickSort recursion depth too large: " + metrics.maxDepth);
    }
}
