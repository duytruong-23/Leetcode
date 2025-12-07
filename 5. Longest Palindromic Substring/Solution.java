class Solution {
    // Brute Force Approach
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty())
            return "";

        if (s.length() == 1)
            return s;

        String longestPalidrome = s.charAt(0) + "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (isPalindrome(s, i, j) && (j - i + 1) > longestPalidrome.length()) {
                    longestPalidrome = s.substring(i, j + 1);
                }
            }
        }
        return longestPalidrome;
    }

    private boolean isPalindrome(String s, int left, int right) {
        if (left < 0 || left >= s.length() || right < 0 || right >= s.length()) {
            return false;
        }

        while (left <= right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }
        return left > right;
    }

    // Expand Around Center Approach
    public String longestPalindromeExpandAroundCenter(String s) {
        if (s == null || s.isEmpty())
            return "";

        if (s.length() == 1)
            return s;

        String longestPalidrome = s.charAt(0) + "";

        for (int i = 0; i < s.length() - 1; i++) {
            // Odd length palindromes
            int left = i;
            int right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if ((right - left + 1) > longestPalidrome.length()) {
                    longestPalidrome = s.substring(left, right + 1);
                }
                left--;
                right++;
            }

            // Even length palindromes
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if ((right - left + 1) > longestPalidrome.length()) {
                    longestPalidrome = s.substring(left, right + 1);
                }
                left--;
                right++;
            }
        }

        return longestPalidrome;
    }

}