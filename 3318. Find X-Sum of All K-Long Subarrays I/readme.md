# Explanation of Approaches

## 1. Sorting-based solution (`calculateXSum`)

- Idea:  
  For each window of size k, maintain a frequency map. Convert map entries to a list, sort by:
  1. frequency descending,
  2. value descending (tie-break).  
     Then take the first x entries and add value × frequency.
- Time complexity:  
  Let n be nums.length, and let m be the number of distinct elements in a window (m ≤ k).  
  Building/updating sliding window map across all windows: O(n).  
  Per window sorting: O(m log m).  
  Number of windows: (n − k + 1).  
  Total: O((n − k + 1) · m log m), worst case O((n − k + 1) · k log k).
- Space complexity: O(m), worst case O(k).

## 2. Max-heap solution (`calculateXSumUsingMaxHeap`)

- Idea:  
  For each window, keep the frequency map. Push all (value, frequency) pairs into a max-heap ordered by:
  1. frequency descending,
  2. value descending.  
     Pop top x elements and sum value × frequency.
- Time complexity:  
  Per window: building heap from m entries is O(m log m), then extracting x is O(x log m).  
  So per window O((m + x) log m).  
  Total: O((n − k + 1) · (m + x) log m), worst case O((n − k + 1) · (k + x) log k).  
  Since x ≤ m ≤ k, this is often simplified to O((n − k + 1) · k log k).
- Space complexity: O(m) for map + heap/list structures, worst case O(k).

Quick note:

- In your current implementation of `findXSum`, the active method is heap-based (`calculateXSumUsingMaxHeap`).
- The sorting-based method is an alternative with similar asymptotic complexity per window.
