import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        String[] phoneLetterMap = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        List<Integer> digitList = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            digitList.add(digits.charAt(i) - '0');
        }

        backtracking(result, digitList, phoneLetterMap, 0, "");
        return result;
    }

    private void backtracking(List<String> result, List<Integer> digitList, String[] phoneLetterMap, int digitIndex,
            String combination) {
        if (combination.length() == digitList.size()) {
            result.add(combination);
            return;
        }

        if (digitIndex >= digitList.size()) {
            return;
        }

        String characters = phoneLetterMap[digitList.get(digitIndex)];
        for (Character c : characters.toCharArray()) {
            backtracking(result, digitList, phoneLetterMap, digitIndex + 1, combination + c);
        }

    }

    public List<String> letterCombinationsIteration(String digits) {
        List<String> combinations = new ArrayList<>();
        combinations.add("");
        String[] phoneLetterMap = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        for (Character d : digits.toCharArray()) {
            List<String> tmpCombinations = new ArrayList<>();
            for (String combination : combinations) {
                for (Character letter : phoneLetterMap[d - '0'].toCharArray()) {
                    tmpCombinations.add(combination + letter);
                }
            }
            combinations = tmpCombinations;
        }

        return combinations;
    }
}
