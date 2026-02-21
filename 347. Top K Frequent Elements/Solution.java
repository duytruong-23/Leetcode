import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        for (Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            treeMap.computeIfAbsent(entry.getValue(), key -> new ArrayList<>()).add(entry.getKey());
        }

        int[] result = new int[k];
        int i = 0;
        while (!treeMap.isEmpty() && i < k) {
            List<Integer> elements = treeMap.pollLastEntry().getValue();
            for (Integer element : elements) {
                if (i == k) {
                    return result;
                }
                result[i++] = element;
            }
        }

        return result;
    }

    // Bucket sort
    // Time: O(n) - For worst cases, it can be O(n ^ 2)
    // value to result
    // Space: O(n)
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] buckets = new List[nums.length + 1];
        for (Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int frequency = entry.getValue();
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(entry.getKey());
        }

        int[] result = new int[k];
        int current = 0;
        for (int i = nums.length; i >= 0 && current < k; i--) {
            if (buckets[i] == null) {
                continue;
            }

            List<Integer> elements = buckets[i];
            for (Integer el : elements) {
                if (current >= k) {
                    return result;
                }
                result[current++] = el;
            }
        }

        return result;
    }

    // Time: O(n*log(n))
    // Space: O(n)
    public int[] topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> maxHeapByFrequency = new PriorityQueue<>(
                (a, b) -> Integer.compare(frequencyMap.getOrDefault(b, 0), frequencyMap.getOrDefault(a, 0)));

        maxHeapByFrequency.addAll(frequencyMap.keySet());

        int[] result = new int[k];
        for (int i = 0; i < k && !maxHeapByFrequency.isEmpty(); i++) {
            result[i] = maxHeapByFrequency.poll();
        }

        return result;
    }
}