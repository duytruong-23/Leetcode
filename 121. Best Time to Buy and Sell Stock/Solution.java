import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int l = 0;
        int r = n - 1;
        Map<Integer, Map<Integer, Integer>> memo = new HashMap<>();

        return maxProfitHelper(prices, l, r, memo);
    }

    private int maxProfitHelper(int[] prices, int l, int r, Map<Integer, Map<Integer, Integer>> memo) {
        if (l >= r) {
            return 0;
        }

        if (memo.containsKey(l) && memo.get(l).containsKey(r)) {
            return memo.get(l).get(r);
        }

        int leftJump = maxProfitHelper(prices, l + 1, r, memo);
        int rightJump = maxProfitHelper(prices, l, r - 1, memo);

        int result = Math.max(Math.max(leftJump, rightJump), prices[r] - prices[l]);
        memo.computeIfAbsent(l, key -> new HashMap<>()).put(r, result);
        return result;
    }

    public int maxProfitUsingKadane(int[] prices) {
        int n = prices.length;
        int buy = prices[0];
        int profit = 0; // max global

        for (int i = 1; i < n; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else if (prices[i] - buy > profit) {
                profit = prices[i] - buy;
            }
        }

        return profit;

    }

    public int maxProfitUsingTwoPointers(int[] prices) {
        int left = prices[0];
        int maxProfit = 0;
        for (int right : prices) {
            if (right > left) {
                maxProfit = Math.max(maxProfit, right - left);
            } else {
                left = right;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        System.out.println(solution.maxProfit(prices));
    }
}
