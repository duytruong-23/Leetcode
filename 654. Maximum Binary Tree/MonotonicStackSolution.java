import java.util.Stack;

public class MonotonicStackSolution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Stack<TreeNode> stack = new Stack<>();

        for (int num : nums) {
            TreeNode current = new TreeNode(num);
            while (!stack.isEmpty() && num > stack.peek().val) {
                current.left = stack.pop();
            }

            if (!stack.isEmpty()) {
                stack.peek().right = current;
            }

            stack.push(current);
        }

        return stack.firstElement();
    }
}
