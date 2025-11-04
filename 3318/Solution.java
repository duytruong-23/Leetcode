import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int resultLength = nums.length - k + 1;
        int[] result = new int[resultLength];

        Map<Integer, Integer> globalFreqMap = new HashMap<>();
        for (int i = 0; i < k; i++) {
            globalFreqMap.put(nums[i], globalFreqMap.getOrDefault(nums[i], 0) + 1);
        }

        result[0] = calculateXSumUsingMaxHeap(globalFreqMap, x);

        for (int i = 1; i < resultLength; i++) {
            int nextTailIndex = i + k - 1;
            int previousHeadIndex = i - 1;

            int headFreq = globalFreqMap.get(nums[previousHeadIndex]);
            if (headFreq > 1) {
                globalFreqMap.put(nums[previousHeadIndex], headFreq - 1);
            } else {
                globalFreqMap.remove(nums[previousHeadIndex]);
            }

            globalFreqMap.put(nums[nextTailIndex], globalFreqMap.getOrDefault(nums[nextTailIndex], 0) + 1);

            result[i] = calculateXSumUsingMaxHeap(globalFreqMap, x);
        }

        return result;

    }

    private int calculateXSum(Map<Integer, Integer> freqMap, int x) {
        int xSum = 0;
        List<Entry<Integer, Integer>> freqList = new ArrayList<>(freqMap.entrySet());
        freqList.sort((a, b) -> {
            if (b.getValue().equals(a.getValue())) {
                return b.getKey() - a.getKey();
            }
            return b.getValue() - a.getValue();
        });

        int numsCounted = Math.min(x, freqList.size());

        for (int i = 0; i < numsCounted; i++) {
            xSum += freqList.get(i).getKey() * freqList.get(i).getValue();
        }

        return xSum;
    }

    private int calculateXSumUsingMaxHeap(Map<Integer, Integer> freqMap, int x) {
        int xSum = 0;
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            if (b[1] != a[1]) {
                return b[1] - a[1];
            }
            return b[0] - a[0];
        });

        for (Entry<Integer, Integer> entry : freqMap.entrySet()) {
            maxHeap.offer(new int[] { entry.getKey(), entry.getValue() });
        }

        int numsCounted = Math.min(x, maxHeap.size());

        for (int i = 0; i < numsCounted; i++) {
            int[] top = maxHeap.poll();
            xSum += top[0] * top[1];
        }

        return xSum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = { 1, 1, 2, 2, 3, 4, 2, 3 };
        int k = 6;
        int x = 2;
        int[] result = solution.findXSum(nums, k, x);
        for (int val : result) {
            System.out.print(val + " ");
        }
    }

}