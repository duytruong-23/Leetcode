import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final String[] KEYPAD = {
            "", // 0
            "", // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs", // 7
            "tuv", // 8
            "wxyz" // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        letterCombinationHelper(digits, 0, "", result);

        return result;
    }

    private void letterCombinationHelper(String digits, int i, String combination, List<String> result) {
        if (i >= digits.length()) {
            result.add(combination);
            return;
        }

        int number = digits.charAt(i) - '0';
        String letters = KEYPAD[number];

        for (char c : letters.toCharArray()) {
            letterCombinationHelper(digits, i + 1, combination + c, result);
        }
    }
}