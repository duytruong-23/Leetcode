public class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean isLeftNodeBalance = isBalanced(root.left);
        boolean isRightNodeBalance = isBalanced(root.right);

        if (!isLeftNodeBalance || !isRightNodeBalance) {
            return false;
        }

        int depthLeft = calculateDepth(root.left);
        int depthRight = calculateDepth(root.right);

        return Math.abs(depthLeft - depthRight) <= 1;
    }

    private int calculateDepth(TreeNode rooNode) {
        if (rooNode == null) {
            return 0;
        }

        int depthLeft = calculateDepth(rooNode.left);
        int depthRight = calculateDepth(rooNode.right);

        return 1 + Math.max(depthLeft, depthRight);
    }
}