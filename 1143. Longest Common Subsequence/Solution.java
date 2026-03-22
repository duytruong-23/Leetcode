public class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        Integer[][] dp = new Integer[n][m];
        return dfs(text1, text2, 0, 0, dp);
    }

    // Top-down
    private int dfs(String text1, String text2, int i, int j, Integer[][] dp) {
        if (i >= text1.length() || j >= text2.length()) {
            return 0;
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (text1.charAt(i) == text2.charAt(j)) {
            dp[i][j] = 1 + dfs(text1, text2, i + 1, j + 1, dp);
        } else {
            dp[i][j] = Math.max(dfs(text1, text2, i + 1, j, dp), dfs(text1, text2, i, j + 1, dp));
        }

        return dp[i][j];
    }

    // Bottom - up
    public int longestCommonSubsequenceBottomUpCheck(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        String text1 = "psnw";
        String text2 = "vozsh";
        Solution solution = new Solution();
        System.out.println(solution.longestCommonSubsequenceBottomUpCheck(text1, text2));
    }

}