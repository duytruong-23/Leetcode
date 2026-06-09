import java.util.Stack;

class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && stack.peek() > c && k > 0) {
                stack.pop();
                k--;
            }

            stack.push(c);
        }

        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }

        int startingFromIndex = 0;
        while (startingFromIndex < stack.size() && stack.elementAt(startingFromIndex) == '0') {
            startingFromIndex++;
        }

        // stack is empty or all the elements are 0s
        if (stack.isEmpty() || startingFromIndex == stack.size()) {
            return "0";
        }

        StringBuilder resultBuilder = new StringBuilder();
        for (int i = startingFromIndex; i < stack.size(); i++) {
            Character c = stack.elementAt(i);
            resultBuilder.append(c);
        }

        return resultBuilder.toString();
    }
}