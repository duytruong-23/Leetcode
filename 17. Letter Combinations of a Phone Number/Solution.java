
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        List<Character>[] letters = setUpLettersMap();
        List<Integer> digitList = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            digitList.add(digits.charAt(i) - '0');
        }

        backtracking(result, digitList, letters, 0, new ArrayList<>());
        return result;
    }

    private void backtracking(List<String> result, List<Integer> digitList, List<Character>[] letters, int digitIndex,
            List<Character> combination) {
        if (combination.size() == digitList.size()) {
            result.add(combination.stream().map(String::valueOf).collect(Collectors.joining()));
            return;
        }

        if (digitIndex >= digitList.size()) {
            return;
        }

        List<Character> characters = letters[digitList.get(digitIndex)];
        for (Character c : characters) {
            combination.add(c);
            backtracking(result, digitList, letters, digitIndex + 1, combination);
            combination.remove(combination.size() - 1);
        }

    }

    private List<Character>[] setUpLettersMap() {
        List<Character>[] letters = new List[10];
        letters[2] = List.of('a', 'b', 'c');
        letters[3] = List.of('d', 'e', 'f');
        letters[4] = List.of('g', 'h', 'i');
        letters[5] = List.of('j', 'k', 'l');
        letters[6] = List.of('m', 'n', 'o');
        letters[7] = List.of('p', 'q', 'r', 's');
        letters[8] = List.of('t', 'u', 'v');
        letters[9] = List.of('w', 'x', 'y', 'z');

        return letters;
    }
}