import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();

        optimizedBacktracking(n, k, result, combination, 1);

        return result;
    }

    // Time: O(n ^ k)
    // Space: O(k)
    private void backtracking(int n, int k, List<List<Integer>> result, List<Integer> combination, int start) {
        if (combination.size() == k) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i <= n; i++) {
            combination.add(i);
            backtracking(n, k, result, combination, i + 1);
            combination.remove(combination.size() - 1);
        }
    }

    // Time: O((n - k + 1) * k)
    // Space: O(k)
    private void optimizedBacktracking(int n, int k, List<List<Integer>> result, List<Integer> combination, int start) {
        if (k == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i <= n - k + 1; i++) {
            combination.add(i);
            optimizedBacktracking(n, k - 1, result, combination, i + 1);
            combination.remove(combination.size() - 1);
        }
    }
}