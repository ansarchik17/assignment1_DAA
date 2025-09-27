package assignment1.algorithms;

import assignment1.metrics.Metrics;
import java.util.Random;

public class QuickSort {
    private final Metrics metrics;
    private final Random rnd = new Random();

    public QuickSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        while (low < high) {
            metrics.enterRecursion();

            int pivotIndex = partition(arr, low, high);
            // Recurse on smaller side
            if (pivotIndex - low < high - pivotIndex) {
                quickSort(arr, low, pivotIndex - 1);
                low = pivotIndex + 1; // iterate on larger side
            } else {
                quickSort(arr, pivotIndex + 1, high);
                high = pivotIndex - 1; // iterate on larger side
            }

            metrics.exitRecursion();
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivotIndex = low + rnd.nextInt(high - low + 1);
        swap(arr, pivotIndex, high); // move pivot to end
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            metrics.incComparisons();
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
                metrics.incAllocations();
            }
        }
        swap(arr, i + 1, high);
        metrics.incAllocations();
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        metrics.incAllocations();
    }
}
