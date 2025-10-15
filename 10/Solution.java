import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution {
    public boolean isMatch(String s, String p) {
        return new Matcher(s, p, 0, 0).match();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isMatch("aab", "c*a*b")); // true
        System.out.println(solution.isMatch("mississippi", "mis*is*p*.")); // false
        System.out.println(solution.isMatch("ab", ".*")); // true
        System.out.println(solution.isMatch("aa", "a")); // false
        System.out.println(solution.isMatch("aa", "a*")); // true
        System.out.println(solution.isMatch("aaa", "a*a")); // true
        System.out.println(solution.isMatch("ab", ".*c")); // false
        System.out.println(solution.isMatch("aaaaaaaaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*")); // true
    }
}

class Matcher {
    private final String input;
    private final String pattern;
    private final Integer inputIndex;
    private final Integer patternIndex;
    private final Map<Matcher, Boolean> memo;

    public Matcher(String input, String pattern, int inputIndex, int patternIndex) {
        this(input, pattern, inputIndex, patternIndex, new HashMap<>());
    }

    public Matcher(String input, String pattern, int inputIndex, int patternIndex, Map<Matcher, Boolean> memo) {
        this.input = input;
        this.pattern = pattern;
        this.inputIndex = inputIndex;
        this.patternIndex = patternIndex;
        this.memo = memo;
    }

    public boolean match() {
        if (memo.containsKey(this)) {
            return memo.get(this);
        }

        if (inputIndex >= input.length() && patternIndex >= pattern.length()) {
            return true;
        } else if (patternIndex >= pattern.length()) {
            return false;
        }

        boolean currentMatch = inputIndex < input.length() &&
                (input.charAt(inputIndex) == pattern.charAt(patternIndex) || pattern.charAt(patternIndex) == '.');

        if (patternIndex + 1 < pattern.length() && pattern.charAt(patternIndex + 1) == '*') {
            // '*' matches zero
            boolean matchZero = new Matcher(input, pattern, inputIndex, patternIndex + 2, memo).match();
            // '*' matches one or more
            boolean matchOneOrMore = currentMatch
                    && new Matcher(input, pattern, inputIndex + 1, patternIndex, memo).match();
            boolean match = matchZero || matchOneOrMore;
            memo.put(this, match);
            return match;
        } else if (currentMatch) {
            boolean match = new Matcher(input, pattern, inputIndex + 1, patternIndex + 1, memo).match();
            memo.put(this, match);
            return match;
        }

        memo.put(this, false);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputIndex, patternIndex, input, pattern);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Matcher)) {
            return false;
        }

        Matcher matcher = (Matcher) o;
        return inputIndex == matcher.inputIndex &&
                patternIndex == matcher.patternIndex &&
                input.equals(matcher.input) &&
                pattern.equals(matcher.pattern);
    }

}