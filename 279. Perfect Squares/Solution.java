public class Solution {
    public int numSquares(int n) {
        return numSquaresBacktracking(n, 0);
    }

    // Time limit exceeded
    private int numSquaresBacktracking(int n, int count) {
        if (n == 0) {
            return count;
        } else if (n < 0) {
            return 0;
        }

        int result = Integer.MAX_VALUE;
        for (int i = n; i >= 1; i--) {
            if (!isPerfectSquare(i)) {
                continue;
            }

            int checkedResult = numSquaresBacktracking(n - i, count + 1);
            if (checkedResult != 0) {
                result = Math.min(checkedResult, result);
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public boolean isPerfectSquare(int n) {
        if (n == 1) {
            return true;
        }

        long end = n / 2;
        long l = 2;
        long r = end;
        while (l <= r) {
            long mid = l - (l - r) / 2;
            long temp = mid * mid;
            if (temp == n) {
                return true;
            } else if (temp > n) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numSquares(12));
    }
}