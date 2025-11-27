import java.util.ArrayList;
import java.util.List;

public class Solution {
    public boolean find132pattern(int[] nums) {
        int[] mins = new int[nums.length];
        mins[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            mins[i] = Math.min(mins[i - 1], nums[i]);
        }

        List<Integer> afters = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > mins[i]) {
                int idx = binarySearchInsertedIndex(afters, nums[i]);
                if (idx > 0 && afters.get(idx - 1) > mins[i]) {
                    return true;
                }

                if (idx == afters.size()) {
                    afters.add(nums[i]);
                } else if (afters.get(idx) != nums[i]) {
                    afters.add(idx, nums[i]);
                }
            }
        }

        return false;
    }

    private int binarySearchInsertedIndex(List<Integer> sortedList, int target) {
        int left = 0;
        int right = sortedList.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = sortedList.get(mid);
            if (midValue == target) {
                return mid; // Target found
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = { 3, 5, 0, 3, 4 };
        System.out.println(solution.find132pattern(nums)); // Output: true
    }
}
