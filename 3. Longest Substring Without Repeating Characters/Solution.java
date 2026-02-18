import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    // Brute force solution
    public int lengthOfLongestSubstring(String s) {
        int longestLength = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            String checkedString = "";
            for (int j = i; j < n; j++) {
                String charStr = String.valueOf(s.charAt(j));
                if (checkedString.contains(charStr)) {
                    break;
                }

                checkedString += charStr;
                longestLength = Math.max(longestLength, checkedString.length());
            }
        }

        return longestLength;
    }

    // Brute force using HashSet
    public int lengthOfLongestSubstring2(String s) {
        int longestLength = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                Character c = s.charAt(j);
                if (set.contains(c)) {
                    break;
                }

                set.add(c);
                longestLength = Math.max(longestLength, j - i + 1);
            }
        }

        return longestLength;
    }

    // Optimized version
    public int lengthOfLongestSubstring3(String s) {
        int longestLength = 0;
        int n = s.length();
        int l = 0;
        int r = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (r < n) {
            Character c = s.charAt(r);
            if (map.containsKey(c)) {
                int i = map.get(c);
                l = Math.max(l, i + 1);
            }

            map.put(c, r);
            longestLength = Math.max(longestLength, r - l + 1);
            r++;
        }

        return longestLength;
    }
}