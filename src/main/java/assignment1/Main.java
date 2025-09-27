package assignment1;

import assignment1.algorithms.MergeSort;
import assignment1.algorithms.QuickSort;
import assignment1.algorithms.DeterministicSelect;
import assignment1.algorithms.ClosestPair;
import assignment1.metrics.CSVWriter;
import assignment1.metrics.Metrics;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        CSVWriter csv = new CSVWriter("results.csv");
        csv.writeHeader();

        int[] sizes = {100, 1000, 5000}; // array sizes for testing

        for (int n : sizes) {
            int[] arr = generateRandomArray(n);  // original array

            // --- MergeSort ---
            int[] arrClone1 = arr.clone();
            Metrics mergeMetrics = new Metrics();
            MergeSort mergeSort = new MergeSort(mergeMetrics);
            long start = System.currentTimeMillis();
            mergeSort.sort(arrClone1);
            long end = System.currentTimeMillis();
            csv.writeRow("MergeSort", n,
                    mergeMetrics.comparisons,
                    mergeMetrics.allocations,
                    mergeMetrics.maxDepth,
                    end - start);

            // --- QuickSort ---
            int[] arrClone2 = arr.clone();
            Metrics quickMetrics = new Metrics();
            QuickSort quickSort = new QuickSort(quickMetrics);
            start = System.currentTimeMillis();
            quickSort.sort(arrClone2);
            end = System.currentTimeMillis();
            csv.writeRow("QuickSort", n,
                    quickMetrics.comparisons,
                    quickMetrics.allocations,
                    quickMetrics.maxDepth,
                    end - start);

            // --- Deterministic Select (Median-of-Medians) ---
            int[] arrClone3 = arr.clone();
            Metrics selectMetrics = new Metrics();
            DeterministicSelect ds = new DeterministicSelect(selectMetrics);
            int k = n / 2; // example: select the median
            start = System.currentTimeMillis();
            int kthValue = ds.selectKth(arrClone3, k);
            end = System.currentTimeMillis();
            csv.writeRow("DeterministicSelect(k=" + k + ")", n,
                    selectMetrics.comparisons,
                    selectMetrics.allocations,
                    selectMetrics.maxDepth,
                    end - start);

            // --- Closest Pair of Points ---
            ClosestPair.Point[] pts = generateRandomPoints(n);
            Metrics cpMetrics = new Metrics();
            ClosestPair cp = new ClosestPair(cpMetrics);
            start = System.currentTimeMillis();
            double bestDist = cp.findClosest(pts);  // fixed method name
            end = System.currentTimeMillis();
            csv.writeRow("ClosestPair", n,
                    cpMetrics.comparisons,
                    cpMetrics.allocations,
                    cpMetrics.maxDepth,
                    end - start);
        }

        csv.close();
        System.out.println("Results written to results.csv");
    }

    private static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(10000);
        return arr;
    }

    private static ClosestPair.Point[] generateRandomPoints(int n) {
        Random rnd = new Random();
        ClosestPair.Point[] pts = new ClosestPair.Point[n];
        for (int i = 0; i < n; i++) {
            double x = rnd.nextDouble() * 10000;
            double y = rnd.nextDouble() * 10000;
            pts[i] = new ClosestPair.Point(x, y);
        }
        return pts;
    }
}
