class Solution {
    public int climbStairs(int n) {
        return climbStairsTopDownHelper(n, 1);
    }

    private int climbStairsTopDownHelper(int n, int current) {
        if (current == n + 1) {
            return 1;
        } else if (current > n + 1) {
            return 0;
        }

        int count = 0;
        for (int i = 1; i <= 2; i++) {
            count += climbStairsTopDownHelper(n, current + i);
        }

        return count;
    }
}