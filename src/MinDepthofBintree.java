/**
 * 
 * @author ChopinXBP
 * Given a binary tree, find its minimum depth. The minimum
 * depth is the number of nodes along the shortest path from the root
 * node down to the nearest leaf node.
 * 
 *
 */

import java.util.ArrayDeque;
import java.util.LinkedList;

public class MinDepthofBintree {

	// Definition for binary tree
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(1);
		root = Init(root);
		int min = Solution(root);
		System.out.println(min);
		// int max = Solution(root);
		// System.out.println(max);
	}

	public static TreeNode Init(TreeNode root) {
		TreeNode pNode = root;
		pNode.left = new TreeNode(2);
		pNode.right = new TreeNode(3);
		pNode = root.left;
		pNode.left = new TreeNode(4);
		//pNode.right = new TreeNode(4);
		//pNode = root.right;
		//pNode.right = new TreeNode(5);
		//pNode.right = new TreeNode(6);
		// pNode = root.left.left;
		// pNode.left = new TreeNode(7);
		// pNode.right = new TreeNode(8);
		// pNode = root.right.left;
		// pNode.left = new TreeNode(9);
		return root;
	}


	public static int Solution(TreeNode root) {
		if (root == null) return 0;
		//队列模拟BFS
		ArrayDeque<TreeNode> treeNode = new ArrayDeque<TreeNode>();
		treeNode.add(root);
		//返回最小深度
		int minDepth = 10000;
		return SearchMin(treeNode, 1, minDepth);
		//返回最大深度
		//int maxDepth = 0;
		//return SearchMax(treeNode, 1, maxDepth);
		
	}

	public static int SearchMin(ArrayDeque<TreeNode> treeNode, int depth, int minDepth) {
		//队列为空时结束遍历
		if (treeNode.isEmpty()) return minDepth;
		//每次一层所有结点出队
		int size = treeNode.size();
		for(int i = 0;i < size;i++){
			TreeNode newroot = treeNode.poll();
			//仅当访问到叶子结点时更新值
			if(newroot.left == null && newroot.right == null){
				if(depth < minDepth) minDepth = depth;
				return minDepth;		//找到第一个叶子结点则为最小深度对应的叶子结点，可以直接返回
			}
			else if(newroot.left == null){
				treeNode.add(newroot.right);
			}
			else if(newroot.right == null){
				treeNode.add(newroot.left);
			}
			else{
				treeNode.add(newroot.left);
				treeNode.add(newroot.right);
			}
		}
		//每增加一层，depth++
		return SearchMin(treeNode, depth + 1, minDepth);		
	}

	public static int SearchMax(ArrayDeque<TreeNode> treeNode, int depth, int maxDepth) {
		//队列为空时结束遍历
		if (treeNode.isEmpty()) return maxDepth - 1;
		//每次一层所有结点出队
		int size = treeNode.size();
		for(int i = 0;i < size;i++){
			TreeNode newroot = treeNode.poll();
			//仅当访问到叶子结点时更新值
			if(newroot.left == null && newroot.right == null){
				if(depth > maxDepth) maxDepth = depth;
			}
			else if(newroot.left == null){
				treeNode.add(newroot.right);
			}
			else if(newroot.right == null){
				treeNode.add(newroot.left);
			}
			else{
				treeNode.add(newroot.left);
				treeNode.add(newroot.right);
			}
		}
		//每增加一层，depth++
		return SearchMax(treeNode, depth + 1, maxDepth);		
	}
	
	
	////////////////////解答参考////////////////////
	//最小深度
    /**
     * 思路1：
     * 深度优先遍历（DFS）
     * 递归比较左右子树深度最小值
     * 如果当前节点是空，则最小深度为 0，返回
     * 效率低
     */
    public int run(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = run(root.left);
        int r = run(root.right);
        if (l == 0 || r == 0) {
            return l + r + 1;
        }
        return Math.min(l, r) + 1;
    }
     
    /**
     * 思路2：
     * 广度优先遍历（BFS）
     * 模拟层序
     * 找到第一个叶子结点就可以停止遍历
     * 效率高
     */
    public int run1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        LinkedList<TreeNode> layerList = new LinkedList<TreeNode>();
        queue.addFirst(root);
        int start = 0;
        int end = 1;
        int level = 1;
        while (!queue.isEmpty()) {
            TreeNode temp = queue.removeLast();
            start++;
            layerList.addFirst(temp);
            if (temp.left == null && temp.right == null) {
                return level;
            }
            if (temp.left != null) {
                queue.addFirst(temp.left);
            }
            if (temp.right != null) {
                queue.addFirst(temp.right);
            }
            if (start == end) {
                level++;
                start = 0;
                end = queue.size();
                layerList = new LinkedList<TreeNode>();
            }
        }
         
        return level;
    }

    //最大深度
    public int maxDepth(TreeNode root) {
    	if(root==null) return 0;
    	return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }
}
