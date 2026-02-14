
public class Solution {

    // Brute force
    public boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }

        int n = num / 2;
        for (int i = 2; i <= n; i++) {
            if (i * i == num) {
                return true;
            }
        }

        return false;
    }

    // Binary Search
    public boolean isPerfectSquareWithBinarySearch(int num) {
        if (num == 1) {
            return true;
        }

        int n = num / 2;
        int l = 2;
        int r = n;
        while (l <= r) {
            int mid = l - (l - r) / 2;
            long squareMid = mid * 1L * mid;
            if (squareMid == num) {
                return true;
            } else if (squareMid < num) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPerfectSquareWithBinarySearch(808201) + "");
    }
}
