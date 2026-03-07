
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int subsetXORSum(int[] nums) {
        return subsetXORSumBacktracking(nums, 0, new ArrayList<>(), 0);
    }

    private int subsetXORSumBacktracking(int[] nums, int start, List<Integer> subset, int currentXOR) {
        currentXOR = calculateXOR(subset);
        int sum = 0;
        for (int i = start; i < nums.length; i++) {
            subset.add(nums[i]);
            sum += subsetXORSumBacktracking(nums, i + 1, subset, currentXOR);
            subset.remove(subset.size() - 1);
        }

        return sum + currentXOR;
    }

    public int calculateXOR(List<Integer> list) {
        int xor = 0;
        for (int num : list) {
            xor ^= num;
        }

        return xor;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3 };
        Solution solution = new Solution();
        System.out.println(solution.subsetXORSum(nums));
    }
}