import java.util.Arrays;
import java.util.Stack;

public class Solution {
    public int[] finalPrices(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int n = prices.length;
        int[] answer = Arrays.copyOf(prices, n);

        for (int i = 0; i < n; i++) {
            while (!stack.empty() && prices[i] <= prices[stack.peek()]) {
                int index = stack.pop();
                answer[index] = prices[index] - prices[i];
            }

            stack.push(i);
        }

        return answer;
    }
}