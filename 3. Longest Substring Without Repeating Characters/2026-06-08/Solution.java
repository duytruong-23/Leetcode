import java.util.HashMap;
import java.util.Map;

class Solution {
    // Time complexity: O(n) - each character is visited at most twice
    // Space complexity: O(min(m, n)) - where m is the size of the character set
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        int currentSubStringLength = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (charIndexMap.containsKey(c) && charIndexMap.get(c) >= i - currentSubStringLength) {
                result = Math.max(result, currentSubStringLength);
                currentSubStringLength = i - charIndexMap.get(c);
            } else {
                currentSubStringLength++;
            }

            charIndexMap.put(c, i);
        }

        return Math.max(result, currentSubStringLength);
    }
}