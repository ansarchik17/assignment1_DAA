package assignment1.algorithms;

import assignment1.metrics.Metrics;

public class MergeSort {

    private final Metrics metrics;

    public MergeSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] arr) {
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int[] buffer, int left, int right) {
        if (left >= right) return;

        metrics.enterRecursion();

        int mid = left + (right - left) / 2;
        mergeSort(arr, buffer, left, mid);
        mergeSort(arr, buffer, mid + 1, right);
        merge(arr, buffer, left, mid, right);

        metrics.exitRecursion();
    }

    private void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            metrics.incComparisons();
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
            metrics.incAllocations();
        }

        while (i <= mid) {
            buffer[k++] = arr[i++];
            metrics.incAllocations();
        }

        while (j <= right) {
            buffer[k++] = arr[j++];
            metrics.incAllocations();
        }

        // Copy back to original array
        for (int p = left; p <= right; p++) {
            arr[p] = buffer[p];
            metrics.incAllocations();
        }
    }
}
