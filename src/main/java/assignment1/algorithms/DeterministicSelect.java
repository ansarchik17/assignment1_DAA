package assignment1.algorithms;

import assignment1.metrics.Metrics;

import java.util.Arrays;

public class DeterministicSelect {
    private final Metrics metrics;

    public DeterministicSelect(Metrics metrics) {
        this.metrics = metrics;
    }

    /**
     * Public entry: returns the k-th smallest value (0-based).
     */
    public int selectKth(int[] arr, int k) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("empty array");
        if (k < 0 || k >= arr.length) throw new IllegalArgumentException("k out of range");
        return select(arr, 0, arr.length - 1, k);
    }

    /**
     * select on subarray [left..right], k is absolute index in array (0..n-1).
     * Returns the k-th smallest value.
     */
    private int select(int[] arr, int left, int right, int k) {
        metrics.enterRecursion();
        try {
            while (true) {
                // If the segment is tiny, sort it and return
                if (left == right) return arr[left];
                int n = right - left + 1;
                if (n <= 5) {
                    insertionSort(arr, left, right);
                    return arr[k];
                }

                // Get a good pivot value = median of medians
                int pivotValue = medianOfMedians(arr, left, right);

                // Partition around pivotValue and get final pivot index
                int pivotIndex = partitionAroundValue(arr, left, right, pivotValue);

                // Decide which side contains the k-th element (k is absolute)
                if (k == pivotIndex) {
                    return arr[k];
                } else if (k < pivotIndex) {
                    right = pivotIndex - 1;
                } else {
                    left = pivotIndex + 1;
                }
                // loop continues on smaller subproblem (no extra recursion here)
            }
        } finally {
            metrics.exitRecursion();
        }
    }

    private int medianOfMedians(int[] arr, int left, int right) {
        // number of elements
        int n = right - left + 1;
        // If small, just sort and return median value
        if (n <= 5) {
            insertionSort(arr, left, right);
            return arr[left + n / 2];
        }

        // For each group of 5, sort and move its median to the front region
        int store = left;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            insertionSort(arr, i, subRight);
            int medianIndex = i + (subRight - i) / 2;
            swap(arr, store, medianIndex);
            store++;
            metrics.incAllocations(); // we moved median
        }
        // The medians now lie in arr[left .. store-1]
        int numMedians = store - left;
        // Recursively select the median of the medians (absolute index is left + numMedians/2)
        int medianOfMediansValue = select(arr, left, left + numMedians - 1, left + numMedians / 2);
        return medianOfMediansValue;
    }

    private int partitionAroundValue(int[] arr, int left, int right, int pivotValue) {
        // Find one occurrence of pivotValue and move it to end
        int found = left;
        boolean pivotFound = false;
        for (int i = left; i <= right; i++) {
            metrics.incComparisons();
            if (arr[i] == pivotValue) { found = i; pivotFound = true; break; }
        }
        if (pivotFound) {
            swap(arr, found, right);
            metrics.incAllocations();
        } else {
            // pivot value might not be present due to duplicates handling; still proceed using pivotValue
            // (we'll partition relative to pivotValue)
        }

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            metrics.incComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
                metrics.incAllocations();
            }
        }
        // Move pivot into final place
        swap(arr, storeIndex, right);
        metrics.incAllocations();
        return storeIndex;
    }

    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                metrics.incComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    metrics.incAllocations();
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
            metrics.incAllocations();
        }
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
