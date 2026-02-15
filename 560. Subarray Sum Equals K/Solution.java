public class Solution {
    // Brute force
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                int sum = 0;
                for (int l = j; l < j + i; l++) {
                    sum += nums[l];
                }

                if (sum == k) {
                    count++;
                }
            }
        }

        return count;
    }

    // Optimize by re-use previous sum
    public int subarraySum2(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += countSumSubArray(nums, k, i);
        }

        return count;
    }

    private int countSumSubArray(int[] nums, int k, int size) {
        int sum = 0;
        int count = 0;
        for (int i = 0; i < size; i++) {
            sum += nums[i];
        }

        if (sum == k) {
            count++;
        }

        int n = nums.length;
        for (int i = size; i < n; i++) {
            sum = sum + nums[i] - nums[i - size];
            if (sum == k) {
                count++;
            }
        }

        return count;
    }

    // Brute force 2
    public int subarraySum3(int[] nums, int k) {
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }

        return count;
    }
}