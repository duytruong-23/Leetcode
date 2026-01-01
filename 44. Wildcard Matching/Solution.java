import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isMatch(String s, String p) {
        Map<Integer, Map<Integer, Boolean>> memo = new HashMap<>();
        return isMatchHelper(s, p, 0, 0, memo);
    }

    public boolean isMatchHelper(String s, String p, int i, int j, Map<Integer, Map<Integer, Boolean>> memo) {
        int inputLength = s.length();
        int patternLength = p.length();

        if (i >= inputLength && j >= patternLength) {
            return true;
        } else if (i >= inputLength) {
            for (int k = j; k < patternLength; k++) {
                if (p.charAt(k) != '*') {
                    return false;
                }
            }
            return true;
        } else if (j >= patternLength) {
            return false;
        }

        if (memo.containsKey(i)) {
            if (memo.get(i).containsKey(j)) {
                return memo.get(i).get(j);
            }
        }

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            boolean isNextMatch = isMatchHelper(s, p, i + 1, j + 1, memo);
            memo.computeIfAbsent(i, key -> new HashMap<>()).put(j, isNextMatch);
            return isNextMatch;
        }

        if (p.charAt(j) != '*') {
            memo.computeIfAbsent(i, key -> new HashMap<>()).put(j, false);
            return false;
        }

        if (j == patternLength - 1) {
            memo.computeIfAbsent(i, key -> new HashMap<>()).put(j, true);
            return true;
        }

        boolean isMatch = isMatchHelper(s, p, i, j + 1, memo) || isMatchHelper(s, p, i + 1, j, memo);

        memo.computeIfAbsent(i, key -> new HashMap<>()).put(j, isMatch);

        return isMatch;
    }

}