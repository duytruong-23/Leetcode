public class Solution {
    /**
     * Brute force solution
     * Time: O(n)
     * Space: O(n)
     */
    public int findMin(int[] nums) {
        int min = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
        }

        return min;
    }

    /**
     * Binary search solution
     * Time: O(log(n))
     */
    public int findMin2(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int mid = l - (l - r) / 2;
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return nums[l];
    }
}