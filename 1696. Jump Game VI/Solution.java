import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Solution {
    public int maxResult(int[] nums, int k) {
        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(nums.length - 1, nums[nums.length - 1]);
        return solve1(nums, k, 0, memo);
    }

    private int solve1(int[] nums, int k, int i) {
        if (i >= nums.length - 1) {
            return nums[nums.length - 1];
        }

        int score = Integer.MIN_VALUE;
        for (int j = 1; j <= k; j++) {
            score = Math.max(score, nums[i] + solve1(nums, k, i + j));
        }

        return score;
    }

    private int solve1(int[] nums, int k, int i, Map<Integer, Integer> memo) {
        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        int score = Integer.MIN_VALUE;
        for (int j = 1; j <= k && i + j < nums.length; j++) {
            score = Math.max(score, nums[i] + solve1(nums, k, i + j, memo));
        }

        memo.put(i, score);
        return memo.get(i);
    }

}