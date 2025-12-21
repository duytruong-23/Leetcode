import java.util.Arrays;
import java.util.TreeMap;

public class Solution2 {

    public int maxResult(int[] nums, int k) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MIN_VALUE);

        return solve2(nums, k, dp, new TreeMap<>());
    }

    private int solve2(int[] nums, int k, int[] dp) {
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + nums[i]);
            }
        }

        return dp[nums.length - 1];
    }

    private int solve2(int[] nums, int k, int[] dp, TreeMap<Integer, Integer> map) {
        dp[0] = nums[0];
        map.put(dp[0], 1);
        for (int i = 1; i < nums.length; i++) {
            if (i > k) {
                int key = dp[i - k - 1];
                map.put(key, map.get(key) - 1);
                if (map.get(key) == 0) {
                    map.remove(key);
                }
            }

            dp[i] = map.lastKey() + nums[i];
            map.put(dp[i], map.getOrDefault(dp[i], 0) + 1);
        }

        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] nums = { 40, 30, -100, -100, -10, -7, -3, -3 };
        int k = 2;

        System.out.println(solution.maxResult(nums, k));
    }
}
