public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int memoSum = nums[0];
        int maxSum = memoSum;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int checkedSum = memoSum + nums[i];
            if (checkedSum >= nums[i]) {
                memoSum = checkedSum;
            } else {
                memoSum = nums[i];
            }

            maxSum = Math.max(maxSum, memoSum);
        }

        return maxSum;
    }
}
