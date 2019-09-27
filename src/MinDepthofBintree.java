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
		//����ģ��BFS
		ArrayDeque<TreeNode> treeNode = new ArrayDeque<TreeNode>();
		treeNode.add(root);
		//������С���
		int minDepth = 10000;
		return SearchMin(treeNode, 1, minDepth);
		//����������
		//int maxDepth = 0;
		//return SearchMax(treeNode, 1, maxDepth);
		
	}

	public static int SearchMin(ArrayDeque<TreeNode> treeNode, int depth, int minDepth) {
		//����Ϊ��ʱ��������
		if (treeNode.isEmpty()) return minDepth;
		//ÿ��һ�����н�����
		int size = treeNode.size();
		for(int i = 0;i < size;i++){
			TreeNode newroot = treeNode.poll();
			//�������ʵ�Ҷ�ӽ��ʱ����ֵ
			if(newroot.left == null && newroot.right == null){
				if(depth < minDepth) minDepth = depth;
				return minDepth;		//�ҵ���һ��Ҷ�ӽ����Ϊ��С��ȶ�Ӧ��Ҷ�ӽ�㣬����ֱ�ӷ���
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
		//ÿ����һ�㣬depth++
		return SearchMin(treeNode, depth + 1, minDepth);		
	}

	public static int SearchMax(ArrayDeque<TreeNode> treeNode, int depth, int maxDepth) {
		//����Ϊ��ʱ��������
		if (treeNode.isEmpty()) return maxDepth - 1;
		//ÿ��һ�����н�����
		int size = treeNode.size();
		for(int i = 0;i < size;i++){
			TreeNode newroot = treeNode.poll();
			//�������ʵ�Ҷ�ӽ��ʱ����ֵ
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
		//ÿ����һ�㣬depth++
		return SearchMax(treeNode, depth + 1, maxDepth);		
	}
	
	
	////////////////////���ο�////////////////////
	//��С���
    /**
     * ˼·1��
     * ������ȱ�����DFS��
     * �ݹ�Ƚ��������������Сֵ
     * �����ǰ�ڵ��ǿգ�����С���Ϊ 0������
     * Ч�ʵ�
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
     * ˼·2��
     * ������ȱ�����BFS��
     * ģ�����
     * �ҵ���һ��Ҷ�ӽ��Ϳ���ֹͣ����
     * Ч�ʸ�
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

    //������
    public int maxDepth(TreeNode root) {
    	if(root==null) return 0;
    	return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }
}
