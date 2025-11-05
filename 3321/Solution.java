import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Solution {
    private final Comparator<int[]> comparator = (a, b) -> {
        if (a[1] != b[1]) {
            return Integer.compare(b[1], a[1]);
        }
        return Integer.compare(b[0], a[0]);
    };
    private Map<Integer, Integer> freqMap = new HashMap<>();
    private TreeSet<int[]> topXSet = new TreeSet<>(comparator);
    private TreeSet<int[]> restSet = new TreeSet<>(comparator);
    private long currentXSum = 0;

    public long[] findXSum(int[] nums, int k, int x) {
        int resultSize = nums.length - k + 1;
        long[] result = new long[resultSize];

        for (int i = 0; i < k; i++) {
            addNumber(nums[i], x);
        }

        result[0] = currentXSum;

        for (int i = 1; i < resultSize; i++) {
            removeNumber(nums[i - 1], x);
            addNumber(nums[i + k - 1], x);
            result[i] = currentXSum;
        }

        return result;

    }

    private void addNumber(int num, int x) {
        int oldFreq = freqMap.getOrDefault(num, 0);
        int newFreq = oldFreq + 1;
        freqMap.put(num, newFreq);
        int[] oldEntry = new int[] { num, oldFreq };
        int[] newEntry = new int[] { num, newFreq };
        if (oldFreq > 0) {
            long oldContribution = 1L * oldFreq * num;
            if (topXSet.remove(oldEntry)) {
                currentXSum -= oldContribution;
            } else {
                restSet.remove(oldEntry);
            }
        }

        restSet.add(newEntry);

        if (topXSet.size() < x) {
            restSet.pollFirst();
            topXSet.add(newEntry);
            currentXSum += 1L * newFreq * num;
        } else if (comparator.compare(restSet.first(), topXSet.last()) < 0) {
            int[] toMoveToTopX = restSet.pollFirst();
            int[] toMoveToRest = topXSet.pollLast();
            currentXSum += 1L * toMoveToTopX[1] * toMoveToTopX[0] - 1L * toMoveToRest[1] * toMoveToRest[0];
            topXSet.add(toMoveToTopX);
            restSet.add(toMoveToRest);
        }
    }

    private void removeNumber(int num, int x) {
        int oldFreq = freqMap.get(num);
        int newFreq = oldFreq - 1;
        if (newFreq == 0) {
            freqMap.remove(num);
        } else {
            freqMap.put(num, newFreq);
        }
        int[] oldEntry = new int[] { num, oldFreq };
        int[] newEntry = new int[] { num, newFreq };
        long oldContribution = 1L * oldFreq * num;
        if (topXSet.remove(oldEntry)) {
            currentXSum -= oldContribution;
        } else {
            restSet.remove(oldEntry);
        }

        if (newFreq > 0) {
            restSet.add(newEntry);
        }

        if (topXSet.size() < x && !restSet.isEmpty()) {
            int[] toMoveToTopX = restSet.pollFirst();
            topXSet.add(toMoveToTopX);
            currentXSum += 1L * toMoveToTopX[1] * toMoveToTopX[0];
        } else if (!restSet.isEmpty() && comparator.compare(restSet.first(), topXSet.last()) < 0) {
            int[] toMoveToTopX = restSet.pollFirst();
            int[] toMoveToRest = topXSet.pollLast();
            currentXSum += 1L * toMoveToTopX[1] * toMoveToTopX[0] - 1L * toMoveToRest[1] * toMoveToRest[0];
            topXSet.add(toMoveToTopX);
            restSet.add(toMoveToRest);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = { 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000 };
        int k = 6;
        int x = 1;
        long[] result = solution.findXSum(nums, k, x);
        for (long sum : result) {
            System.out.print(sum + " ");
        }
    }

}