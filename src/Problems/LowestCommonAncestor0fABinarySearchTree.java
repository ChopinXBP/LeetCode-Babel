package Problems;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 升级版：给定的是二叉树而非搜索树
 */

public class LowestCommonAncestor0fABinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //求二叉搜索树的最近公共祖先
    //从根节点开始访问，若root值比p和q均小，则根节点移向右子节点；反之若root值比p和q均大，则根节点移向左子节点
    //第一个遍历到的位于两子树结点值之间的结点即为最近公共祖先
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null){
            if(root.val < p.val && root.val < q.val)
                root = root.right;
            else if(root.val > p.val && root.val > q.val)
                root = root.left;
            else
                break;
        }
        return root;
    }

    //升级版：求二叉树的最近公共祖先
    //若二叉树结点有指向父结点的指针，或创立数组建立root到pq的路径，问题可以转换为求两个链表的第一个公共结点
    //若二叉树结点没有指向父节点的指针，可用后序遍历标记法。
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //后序遍历二叉树，若子树中访问到p或q，则将p或q返回，否则返回null，从而进行标记
        if(root == null || root == p || root == q)
            return root;

        //当左右子树均没有访问到p或q时，直接返回null
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null && right == null)
            return null;

        //若左子树或右子树仅有一棵包含p或q，则返回该子节点。
        //若左右子树分别包含p和q，则返回当前结点root。
        //由于root的祖先已经无法在不含root的另一子树上访问到p或q，因此函数递归结束仍然返回该结点root
        return left != null && right != null ? root : (left != null ? left : right);
    }

}
