import java.util.ArrayDeque;
import java.util.Deque;

public class Solution3 {
    public int maxResult(int[] nums, int k) {
        // prepare short-live variables
        Deque<Integer> queue = new ArrayDeque<>();
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        queue.push(0);

        // main idea
        for (int i = 1; i < n; i++) {
            if (queue.peek() < i - k) {
                queue.pollFirst();
            }
            dp[i] = nums[i] + dp[queue.peek()];
            while (!queue.isEmpty() && dp[i] >= dp[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.add(i);
        }

        return dp[n - 1];
    }
}
