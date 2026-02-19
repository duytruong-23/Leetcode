public class Solution {
    public int fib(int n) {
        return fibRecursionWithMemo(n, new int[n + 1]);
    }

    // Time: O(2^n)
    public int fibRecursion(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return fibRecursion(n - 1) + fibRecursion(n - 2);
    }

    // Tine: O(n)
    public int fibRecursionWithMemo(int n, int[] memo) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (memo[n] != 0) {
            return memo[n];
        }

        int res = fibRecursionWithMemo(n - 1, memo) + fibRecursionWithMemo(n - 2, memo);
        memo[n] = res;
        return res;
    }

    // Bottom-up (eliminate recursion)
    // Time: O(n)
    public int fibRecursion2(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;

        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }

        return memo[n];
    }
}