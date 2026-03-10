
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int maxDepth(TreeNode root) {
        return dfs(root);
    }

    // Time: O(n)
    // Space: O(max_h): in worst case, max_h = n
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(dfs(root.left), dfs(root.right)) + 1;
    }

    // Time: O(n): despite nested loop, only n iterations total
    // Space: O(n / 2): complete binary tree
    private int bfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            depth++;
            int currentQueueSize = queue.size();
            while (currentQueueSize > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
                currentQueueSize--;
            }
        }

        return depth;
    }
}