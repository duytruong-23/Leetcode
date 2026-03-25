import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class OptimizedSolution {
    // Time Complexity: O(n1 + n2), where n1 and n2 are the lengths of nums1 and
    // nums2 respectively. We traverse nums2 once to build the map and then
    // traverse nums1 once to build the result.
    // Space Complexity: O(n1 + n2), where n1 and n2 are the lengths of nums1 and
    // nums2 respectively. We use a stack to store elements of nums2 and a map to
    // store the next greater elements, which can take up to O(n2) space. The result
    // array takes O(n1) space. Overall, the space complexity is O(n1 + n2).
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
