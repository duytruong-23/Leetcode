class Solution {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }

        int left = 1;
        int right = x;
        while (left <= right) {
            long mid = 1l * (left + (right - left) / 2);
            long square = mid * mid;
            if (square == x) {
                return (int) mid;
            } else if (square < x) {
                left = (int) mid + 1;
            } else {
                right = (int) mid - 1;
            }

        }

        return right;
    }
}