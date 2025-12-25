import java.util.Arrays;

class Solution {
    private static int MAXIMUM_STEP = 2;

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 0);
        dp[n - 1] = 1;
        return climbStairsHelperWithMemo(n, n, dp);
    }

    private int climbStairsHelper(int n, int currentPos) {
        if (currentPos == n - 1) {
            return 1;
        } else if (currentPos > n - 1) {
            return 0;
        }

        int total = 0;
        for (int i = 1; i <= MAXIMUM_STEP; i++) {
            total += climbStairsHelper(n, currentPos + i);
        }

        return total;
    }

    private int climbStairsHelperWithMemo(int n, int currentPos, int[] dp) {
        if (currentPos > n) {
            return 0;
        }

        if (dp[currentPos] != 0) {
            return dp[currentPos];
        }

        for (int i = 1; i <= MAXIMUM_STEP; i++) {
            int nextPos = (currentPos + i) % (n + 1);
            if (nextPos <= n - 1) {
                dp[currentPos] += climbStairsHelperWithMemo(n, nextPos, dp);
            }

        }

        return dp[currentPos];
    }

    // Bottom - top approach
    public int climbStairs2(int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 0);
        return climbStairsHelperBottomTopWithMemo(n, n - 1, dp);
    }

    private int climbStairsHelperBottomTop(int n, int currentPos) {
        if (currentPos == -1) {
            return 1;
        }

        if (currentPos < -1) {
            return 0;
        }

        if (currentPos < MAXIMUM_STEP) {
            return currentPos + 1;
        }

        int total = 0;
        for (int i = 1; i <= MAXIMUM_STEP; i++) {
            total += climbStairsHelperBottomTop(n, currentPos - i);
        }

        return total;
    }

    private int climbStairsHelperBottomTopWithMemo(int n, int currentPos, int[] dp) {
        if (currentPos == -1) {
            return 1;
        }

        if (currentPos < -1) {
            return 0;
        }

        if (dp[currentPos] != 0) {
            return dp[currentPos];
        }

        for (int i = 1; i <= MAXIMUM_STEP; i++) {
            dp[currentPos] += climbStairsHelperBottomTopWithMemo(n, currentPos - i, dp);
        }

        return dp[currentPos];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.climbStairs2(3));
    }
}