public class Solution {
    public int minCost(String colors, int[] neededTime) {
        int totalCost = 0;
        int numColors = colors.length();
        int i = 0;
        while (i < numColors - 1) {
            if (colors.charAt(i) == colors.charAt(i + 1)) {
                int j = i + 1;
                int maxTime = neededTime[i];
                int sumTime = neededTime[i];
                while (j < numColors && colors.charAt(j) == colors.charAt(i)) {
                    sumTime += neededTime[j];
                    maxTime = Math.max(maxTime, neededTime[j]);
                    j++;
                }
                totalCost += sumTime - maxTime;
                i = j;
                continue;
            }

            i++;
        }

        return totalCost;
    }

    public int minCostOptimized(String colors, int[] neededTime) {
        int totalCost = 0;
        int storedMax = 0;
        int numColors = colors.length();

        for (int i = 0; i < numColors - 1; i++) {
            if (colors.charAt(i) == colors.charAt(i + 1)) {
                if (storedMax == 0) {
                    storedMax = neededTime[i];
                }

                totalCost += Math.min(storedMax, neededTime[i + 1]);
                storedMax = Math.max(storedMax, neededTime[i + 1]);
            } else {
                storedMax = 0;
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String colors = "aaabbbabbbb";
        int[] neededTime = { 3, 5, 10, 7, 5, 3, 5, 5, 4, 8, 1 };
        int result = solution.minCost(colors, neededTime);
        System.out.println("Minimum cost to make the rope colorful: " + result);
    }
}