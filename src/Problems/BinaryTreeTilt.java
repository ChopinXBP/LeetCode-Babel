package Problems;

public class BinaryTreeTilt {

    public int findTilt(TreeNode root) {
        solution(root);
        return result;
    }

    private int result;

    private int solution(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int left = solution(root.left);
        int right = solution(root.right);
        result += Math.abs(left - right);
        return root.val + left + right;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
