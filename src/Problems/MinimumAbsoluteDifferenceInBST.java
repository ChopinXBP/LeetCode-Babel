package Problems;

public class MinimumAbsoluteDifferenceInBST {

    public int getMinimumDifference(TreeNode root) {
        solution(root);
        return result;
    }

    private int result = Integer.MAX_VALUE;

    private int lastValue = -1;

    private void solution(TreeNode root) {
        if(root == null) {
            return;
        }
        solution(root.left);
        if(lastValue != -1){
            int newVal = root.val - lastValue;
            result = newVal < result ? newVal : result;
        }
        lastValue = root.val;
        solution(root.right);
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
