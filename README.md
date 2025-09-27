Assignment 1: Algorithm Analysis

## Architecture Notes

- **Project Structure**:
  - `algorithms/` → Sorting and selection algorithms:
    - `MergeSort.java`
    - `QuickSort.java`
    - `DeterministicSelect.java`
    - `ClosestPair.java`
  - `metrics/` → `Metrics.java` tracks recursion depth, comparisons, and allocations.
  - `Main.java` → Runs experiments on arrays of different sizes and writes results to `results.csv`.
- **Depth & Allocations**:
  - **Recursion Depth**: Incremented at each recursive call with `enterRecursion()`, decremented with `exitRecursion()`.
  - **Allocations**: Tracked when buffers are created or array elements are copied.
  - Each algorithm uses its **own `Metrics` object** to avoid mixing data.


##Recurrence Analysis

**MergeSort**  
Recursively divides the array into halves: `T(n) = 2T(n/2) + Θ(n) → Θ(n log n)`. Recursion depth = log2 n.

**QuickSort (Randomized Pivot)**  
Partitions array around a pivot. Average case: `T(n) = 2T(n/2) + Θ(n) → Θ(n log n)`. Worst-case = Θ(n²), but rare due to random pivot. Recursion depth ~ 2*log2 n.

**Deterministic Select (Median-of-Medians)**  
Groups array into 5, selects median-of-medians as pivot: `T(n) = T(n/5) + T(7n/10) + Θ(n) → Θ(n)`. Guarantees linear selection. Depth smaller than QuickSort.

**Closest Pair (2D)**  
Divide-and-conquer by x-coordinate, check middle strip: `T(n) = 2T(n/2) + Θ(n) → Θ(n log n)`.  


##Excel:<img width="660" height="301" alt="Screenshot 2025-09-27 at 21 26 37" src="https://github.com/user-attachments/assets/6e61d78e-04df-4d88-82ba-67416d8117f4" />

##Plots
1)[merge sort time vs n](https://github.com/user-attachments/assets/9ab931c5-cf20-4d51-8062-f774bad6b2af)
2)[merge sort depth vs n](https://github.com/user-attachments/assets/a5f8eef4-7c18-4197-8153-91b1c06dab0e)
3)[quick sort time vs n](https://github.com/user-attachments/assets/0d81dd35-577d-4074-9e28-a4e8a830e832)
4)[quick sort depth vs n](https://github.com/user-attachments/assets/56cb97d9-c1d9-47f9-ae6f-673c2cc09861)
5)[deterministicselect time vs n](https://github.com/user-attachments/assets/b90a9567-31ac-48b8-8944-7cffc357a846)
6)[deterministicselect depth vs n](https://github.com/user-attachments/assets/2d29cbc5-0d61-4431-b418-7803a34c8213)
7)[closestpair time vs n](https://github.com/user-attachments/assets/123d1f47-9e06-4715-9845-d23194c9525e)
8)[closestpair depth vs n](https://github.com/user-attachments/assets/b51329dc-99e2-40e4-b9ef-fbd742608024)

**Discussion:**  
- QuickSort is faster than MergeSort for small n due to better cache locality.  
- MergeSort allocations are higher (buffer arrays), slightly slowing small n.  
- DeterministicSelect scales linearly, depth lower than QuickSort.  


##Summary

- **MergeSort:** Matches Θ(n log n). Recursion depth log2 n as expected.  
- **QuickSort:** Average case Θ(n log n). Depth roughly 2*log2 n. Faster in practice for small arrays.  
- **DeterministicSelect:** Linear time observed. Depth smaller than QuickSort.  
- **ClosestPair:** Confirms Θ(n log n) scaling; brute-force used only for validation of small n.  

Overall, experimental results align with theoretical expectations. Minor deviations are due to memory allocations, cache effects, and Java garbage collection overhead.


##Notes

- CSV results include all algorithms for direct comparison.
- Graphs were created in Google Sheets and exported as PNG.
