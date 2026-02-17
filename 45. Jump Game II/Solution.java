public class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }

        int[] memo = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            jumpWithMemo2(nums, i, memo);
        }

        return memo[0];
    }

    // Top-down
    private int jump(int[] nums, int i) {
        if (i == nums.length - 1) {
            return 0;
        }

        if (i > nums.length - 1 || nums[i] == 0) {
            return nums.length - 1;
        }

        int min = nums.length - 1;
        for (int j = 1; j <= nums[i]; j++) {
            min = Math.min(min, jump(nums, i + j) + 1);
        }

        return min;
    }

    // Top-down
    private int jumpWithMemo(int[] nums, int i, int[] memo) {
        if (i == nums.length - 1) {
            return 0;
        }

        if (i > nums.length - 1 || nums[i] == 0) {
            return nums.length - 1;
        }

        if (memo[i] != 0) {
            return memo[i];
        }

        int min = nums.length - 1;
        for (int j = 1; j <= nums[i]; j++) {
            min = Math.min(min, jumpWithMemo(nums, i + j, memo) + 1);
        }

        memo[i] = min;
        return min;
    }

    // Bottom-up
    private void jumpWithMemo2(int[] nums, int i, int[] memo) {
        if (nums.length - 1 - i <= nums[i]) {
            memo[i] = 1;
            return;
        }

        if (nums[i] == 0) {
            memo[i] = nums.length - 1;
            return;
        }

        int min = memo[i + 1] + 1;
        for (int j = 1; j <= nums[i]; j++) {
            min = Math.min(min, memo[i + j] + 1);
        }

        memo[i] = min;
    }

    private int jumpUsingGreedy(int[] nums) {
        int n = nums.length;

        int jumps = 0;
        int end = 0;
        int longestIndex = 0;
        for (int i = 0; i < n - 1; i++) {
            longestIndex = Math.max(longestIndex, i + nums[i]);
            if (i == end) {
                jumps++;
                end = longestIndex;
            }
        }

        return jumps;
    }
}