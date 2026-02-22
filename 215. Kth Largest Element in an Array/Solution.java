import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    // Sort
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        return nums[n - k];
    }

    // Without sort
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : nums) {
            priorityQueue.add(num);
        }

        int answer = 0;
        for (int i = 1; i <= k; i++) {
            answer = priorityQueue.poll();
        }

        return answer;
    }
}