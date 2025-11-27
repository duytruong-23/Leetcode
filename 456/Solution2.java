import java.util.Stack;

public class Solution2 {
    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int two = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (two > nums[i]) {
                return true;
            }

            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                two = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] nums = { 2, 5, 3, 1 };
        System.out.println(solution.find132pattern(nums)); // Output: true
    }
}
