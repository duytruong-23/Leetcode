
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    // Time: O(n ^ 2)
    public int maxProduct(int[] nums) {
        int max = 0;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                max = Math.max(max, (nums[i] - 1) * (nums[j] - 1));
            }
        }

        return max;
    }

    // Time: O(n * log(n))
    public int maxProduct2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return (nums[n - 1] - 1) * (nums[n - 2] - 1);
    }

    // Time: O(n * log(n))
    public int maxProduct3(int[] nums) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : nums) {
            priorityQueue.add(num);
        }

        int num1 = priorityQueue.poll();
        int num2 = priorityQueue.poll();
        return (num1 - 1) * (num2 - 1);
    }

    // O(n)
    public int maxProduct4(int[] nums) {
        int max1 = 0;
        int max2 = 0;

        for (int num : nums) {
            if (num >= max1) {
                max2 = max1;
                max1 = num;
            } else if (num >= max2) {
                max2 = num;
            }
        }

        return (max1 - 1) * (max2 - 1);
    }
}