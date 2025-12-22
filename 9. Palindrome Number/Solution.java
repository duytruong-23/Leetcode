class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int clonedX = x;
        int y = 0;
        while (clonedX > 0) {
            int digit = clonedX % 10;
            clonedX = clonedX / 10;
            y = y * 10 + digit;
        }

        return y == x;
    }

}