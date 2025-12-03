import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < k; i++) {
            while (!stack.empty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            stack.push(i);
        }

        result[0] = nums[stack.get(0)];

        for (int i = k; i < nums.length; i++) {
            int currentItem = nums[i];

            while (!stack.empty() && nums[stack.peek()] < currentItem) {
                stack.pop();
            }

            stack.push(i);
            if (stack.get(0) < i - k + 1) {
                stack.remove(0);
            }
            result[i - k + 1] = nums[stack.get(0)];
        }

        return result;
    }
}