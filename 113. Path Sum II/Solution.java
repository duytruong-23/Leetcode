import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        pathSumHelper(root, targetSum, result, new ArrayList<>());
        return result;
    }

    public void pathSumHelper(TreeNode root, int targetSum, List<List<Integer>> result, List<Integer> path) {
        if(root == null) {
            return;
        }

        path.add(root.val);
        if(root.left == null && root.right == null && root.val == targetSum) {
            result.add(new ArrayList<>(path));
        }

        pathSumHelper(root.left, targetSum - root.val, result, path);
        pathSumHelper(root.right, targetSum - root.val, result, path);

        path.remove(path.size() - 1);
    }
}
