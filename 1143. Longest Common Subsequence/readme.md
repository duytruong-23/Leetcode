Here is the LCS table for the strings "abcde" and "ace":
| | a | c | e | ∅ |
|---|---|---|---|---|
| a | 3 | 2 | 1 | 0 |
| b | 2 | 2 | 1 | 0 |
| c | 2 | 2 | 1 | 0 |
| d | 1 | 1 | 1 | 0 |
| e | 1 | 1 | 1 | 0 |
| ∅ | 0 | 0 | 0 | 0 |

The longest common subsequence of "abcde" and "ace" is "ace", which has a length of 3.

## Top-down approach with memoization

- Time: O(nm)
- Space: O(nm) for memo + O(n + m) recursion stack, so total O(nm) overall
- Why:
  Each state is identified by (i, j), where i is an index in text1 and j is an index in text2. There are at most n × m such states. Because of memoization, each state is computed once, then reused.
- The extra recursion stack comes from following a path that increments i or j until the end, with max depth about n + m.

## Bottom-up approach with tabulation

- Time: O(nm)
- Space: O(nm)
- Why:
  We fill in a table of size (n+1) × (m+1) where
  each cell (i, j) is computed based on previously computed cells. We compute each cell once, and there are (n+1) × (m+1) cells, leading to O(nm) time. The space is O(nm) because we store the entire table.
