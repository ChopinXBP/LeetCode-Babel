package Problems;

import java.util.HashMap;

/**
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * You may assume that duplicates do not exist in the tree.
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 你可以假设树中没有重复的元素。
 *
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //利用哈希表存储每一个结点的位置，再进行递归建树
    int[] preOrder;
    int[] inOrder;
    int[] postOrder;
    HashMap<Integer, Integer> map = new HashMap<>();

    //先序遍历 + 中序遍历 -> 重构二叉树
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preOrder = preorder;
        inOrder = inOrder;
        int idx = 0;
        for(int value : inorder){
            map.put(value, idx++);
        }
        return Solution(0, 0, inorder.length - 1);
    }

    public TreeNode Solution(int rootLocPre, int begin, int end){
        if(rootLocPre >= preOrder.length || begin > end){
            return null;
        }
        TreeNode root = new TreeNode(preOrder[rootLocPre]);
        if(begin != end){
            int rootLocIn = map.get(preOrder[rootLocPre]);
            root.left = Solution(rootLocPre + 1, begin, rootLocIn - 1);
            root.right = Solution(rootLocPre + rootLocIn - begin + 1, rootLocIn + 1, end);
        }
        return root;
    }

    //后序遍历 + 中序遍历 -> 重构二叉树
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        postOrder = postorder;
        inOrder = inOrder;
        int idx = 0;
        for(int value : inorder){
            map.put(value, idx++);
        }
        return Solution2(postorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode Solution2(int rootLocPost, int begin, int end){
        if(rootLocPost < 0 || begin > end){
            return null;
        }
        TreeNode root = new TreeNode(postOrder[rootLocPost]);
        if(begin != end){
            int rootLocIn = map.get(postOrder[rootLocPost]);
            root.left = Solution2(rootLocPost - (end - rootLocIn + 1), begin, rootLocIn - 1);
            root.right = Solution2(rootLocPost - 1, rootLocIn + 1, end);
        }
        return root;
    }
}
