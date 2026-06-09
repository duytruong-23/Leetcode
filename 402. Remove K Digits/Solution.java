import java.util.Stack;

class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (char c : num.toCharArray()) {
            while (k > 0 && !stack.empty() && stack.peek() > c) {
                stack.pop();
                k--;
            }
            stack.add(c);
        }

        while (k > 0 && !stack.empty()) {
            stack.pop();
            k--;
        }

        if (stack.isEmpty()) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        stack.forEach(el -> result.append(String.valueOf(el)));

        String resultStr = result.toString().replaceFirst("^0+(?!$)", "");

        return resultStr;
    }
}