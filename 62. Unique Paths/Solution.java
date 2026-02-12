public class Solution {
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        return memo[0][0];
    }

    /**
     * Top-down approach
     * Time: O(2^(m + n))
     * Space: O(m + n)
     * 
     * @param m
     * @param n
     * @param x
     * @param y
     * @param memo
     * @return
     */
    private int uniquePathsHelper(int m, int n, int x, int y, int[][] memo) {
        if (x == (m - 1) && y == (n - 1)) {
            return 1;
        }

        if (x >= m || y >= n) {
            return 0;
        }

        if (memo[x][y] != 0) {
            return memo[x][y];
        }

        int countIfGoingDown = uniquePathsHelper(m, n, x, y + 1, memo);
        int countIfGoingRight = uniquePathsHelper(m, n, x + 1, y, memo);

        int count = countIfGoingDown + countIfGoingRight;
        memo[x][y] = count;

        return count;
    }

    private int uniquePathsHelperBottomUp(int m, int n) {
        int[][] memo = new int[m][n];
        memo[m - 1][n - 1] = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    continue;
                }

                int down = (i + 1) < m ? memo[i + 1][j] : 0;
                int right = (j + 1) < n ? memo[i][j + 1] : 0;

                memo[i][j] = down + right;
            }
        }

        return memo[0][0];
    }

    private int uniquePathsHelperBottomUp2(int m, int n) {
        int[][] memo = new int[m + 1][n + 1];
        memo[m - 1][n - 1] = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int down = memo[i + 1][j];
                int right = memo[i][j + 1];

                memo[i][j] += down + right;
            }
        }

        return memo[0][0];
    }

}