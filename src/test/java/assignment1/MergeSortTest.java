package assignment1;

import assignment1.algorithms.MergeSort;
import assignment1.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

    @Test
    void testCorrectnessRandom() {
        Random rnd = new Random();
        for (int n = 10; n <= 500; n += 50) {
            int[] arr = rnd.ints(n, 0, 10000).toArray();
            int[] expected = arr.clone();
            Arrays.sort(expected);

            Metrics metrics = new Metrics();
            MergeSort mergeSort = new MergeSort(metrics);
            mergeSort.sort(arr);

            assertArrayEquals(expected, arr, "MergeSort failed for n=" + n);
        }
    }

    @Test
    void testCorrectnessAdversarial() {
        int[] arr = {5,4,3,2,1};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        MergeSort mergeSort = new MergeSort(metrics);
        mergeSort.sort(arr);

        assertArrayEquals(expected, arr, "MergeSort failed on adversarial input");
    }
}
