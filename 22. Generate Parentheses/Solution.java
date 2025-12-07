import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, "", 0, 0, n);
        return result;
    }

    private void generate(final List<String> result, final String current, int open, int close, int maxOpen) {
        if (current.length() == maxOpen * 2) {
            result.add(current);
            return;
        }

        if (open < maxOpen) {
            generate(result, current + "(", open + 1, close, maxOpen);
        }

        if (close < open) {
            generate(result, current + ")", open, close + 1, maxOpen);
        }
    }

}