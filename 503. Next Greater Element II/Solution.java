import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                map.put(stack.pop(), nums[i]);
            }
            stack.push(i);
        }

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                map.put(stack.pop(), nums[i]);
            }
        }

        for (int i = 0; i < n; i++) {
            result[i] = map.getOrDefault(i, -1);
        }

        return result;

    }

    public int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n * 2; i++) {
            int currentNum = nums[i % n];
            while (!stack.isEmpty() && currentNum > nums[stack.peek()]) {
                result[stack.pop()] = currentNum;
            }

            if (i < n) {
                stack.push(i);
            }
        }

        return result;
    }
}
