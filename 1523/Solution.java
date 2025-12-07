class Solution {
    // Bruce Force Approach
    public int countOdds(int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            if (i % 2 != 0) {
                count++;
            }
        }
        return count;
    }

    // Optimal Approach
    public int countOddsOptimal(int low, int high) {
        // Use pattern even -> odd -> even -> odd
        int validLow = (low % 2 == 0) ? low : low + 1;
        int validHigh = (high % 2 == 0) ? high - 1 : high;

        int totalNumbers = validHigh - validLow + 1;
        int oddCount = totalNumbers / 2;

        // If either low or high is odd, we have one extra odd number
        if (low % 2 != 0) {
            oddCount++;
        }

        return oddCount;
    }
}