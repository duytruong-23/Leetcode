import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int numSquares(int n) {
        List<Integer> availablePerfectSquares = new ArrayList<>();
        availablePerfectSquares.add(1);

        for (int i = 2; i * i <= n; i++) {
            availablePerfectSquares.add(i * i);
        }

        int[] memo = new int[n + 1];
        return numSquaresHelper2(n, availablePerfectSquares, memo);
    }

    // Backtracking
    private int numSquaresBacktracking(int n, int count, List<Integer> availablePerfectSquares) {
        if (n == 0) {
            return count;
        }

        int result = Integer.MAX_VALUE;
        for (int num : availablePerfectSquares) {
            if (n - num < 0) {
                break;
            }

            result = Math.min(result,
                    numSquaresBacktracking(n - num, count + 1, availablePerfectSquares));
        }

        return result;
    }

    // Top-down
    private int numSquaresHelper(int n, List<Integer> availablePerfectSquares, int[] memo) {
        if (n == 0) {
            return 0;
        }

        if (memo[n] != 0) {
            return memo[n];
        }

        int result = Integer.MAX_VALUE;
        for (int num : availablePerfectSquares) {
            if (n - num < 0) {
                break;
            }

            result = Math.min(result, numSquaresHelper(n - num, availablePerfectSquares, memo) + 1);
        }

        memo[n] = result;
        return result;
    }

    // Bottom-up
    private int numSquaresHelper2(int n, List<Integer> availablePerfectSquares, int[] memo) {
        for (int i = 1; i <= n; i++) {
            memo[i] = i;
            for (int num : availablePerfectSquares) {
                if (i - num < 0) {
                    break;
                }

                memo[i] = Math.min(memo[i], memo[i - num] + 1);
            }
        }

        return memo[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numSquares(48));
    }
}