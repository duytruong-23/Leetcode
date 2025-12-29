import java.util.Map;

class Solution {
    Map<Character, Integer> romanMap = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000);

    public int romanToInt(String s) {
        int n = s.length();
        int result = romanMap.get(s.charAt(n - 1));
        for (int i = 0; i < n - 1; i++) {
            int currentValue = romanMap.get(s.charAt(i));
            int nextValue = romanMap.get(s.charAt(i + 1));
            int sign = currentValue < nextValue ? -1 : 1;
            result += sign * currentValue;
        }

        return result;
    }
}