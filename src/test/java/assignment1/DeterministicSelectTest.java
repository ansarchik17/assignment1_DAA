package assignment1;

import assignment1.algorithms.DeterministicSelect;
import assignment1.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeterministicSelectTest {

    @Test
    void testCorrectnessAgainstSort() {
        Random rnd = new Random();
        for (int t = 0; t < 100; t++) { // 100 random trials
            int n = rnd.nextInt(200) + 10;
            int[] arr = rnd.ints(n, 0, 10000).toArray();
            int[] sorted = arr.clone();
            Arrays.sort(sorted);

            int k = rnd.nextInt(n);

            Metrics metrics = new Metrics();
            DeterministicSelect ds = new DeterministicSelect(metrics);
            int result = ds.selectKth(arr.clone(), k);

            assertEquals(sorted[k], result,
                    "DeterministicSelect failed on trial " + t + " for k=" + k);
        }
    }
}
