import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class OptimizedSolution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] result = new int[n1];
        Map<Integer, Integer> nextGreaterElementMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n2; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                int key = stack.pop();
                nextGreaterElementMap.put(key, nums2[i]);
            }

            stack.push(nums2[i]);
        }

        for (int i = 0; i < n1; i++) {
            result[i] = nextGreaterElementMap.getOrDefault(nums1[i], -1);
        }

        return result;
    }
}
