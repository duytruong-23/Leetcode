
import java.util.HashMap;
import java.util.Map;

public class OptimizationSolution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        // If prefixSum - k = 0, it means the subarray starts from index 0.
        // We add (0, 1) to handle this case naturally.
        sumMap.put(0, 1);

        int count = 0;
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            int diff = sum - k;
            if (sumMap.containsKey(diff)) {
                count += sumMap.get(diff);
            }

            sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
