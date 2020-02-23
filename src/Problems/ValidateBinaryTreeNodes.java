package Problems;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i],
 * return true if and only if all the given nodes form exactly one valid binary tree.
 * If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
 * Note that the nodes have no values and that we only use the node numbers in this problem.
 * 二叉树上有 n 个节点，按从 0 到 n - 1 编号，其中节点 i 的两个子节点分别是 leftChild[i] 和 rightChild[i]。
 * 只有 所有 节点能够形成且 只 形成 一颗 有效的二叉树时，返回 true；否则返回 false。
 * 如果节点 i 没有左子节点，那么 leftChild[i] 就等于 -1。右子节点也符合该规则。
 * 注意：节点没有值，本问题中仅仅使用节点编号。
 *
 */

public class ValidateBinaryTreeNodes {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        //同一结点不能有两个父节点
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if(leftChild[i] != -1){
                if(map.containsKey(leftChild[i])){
                    return false;
                }
                map.put(leftChild[i], i);
            }
            if(rightChild[i] != -1){
                if(map.containsKey(rightChild[i])){
                    return false;
                }
                map.put(rightChild[i], i);
            }
        }
        //有且仅有一个根节点
        if(map.size() != n - 1){
            return false;
        }
        //结点中不存在环
        int root = -1;
        for (int i = 0; i < n; i++) {
            if(!map.containsKey(i)){
                root = i;
                break;
            }
        }
        HashSet<Integer> set = new HashSet<>();
        search(root, leftChild, rightChild, set);
        return !hasCircle && set.size() == n;
    }

    private boolean hasCircle = false;

    public void search(int root, int[] leftChild, int[] rightChild, HashSet<Integer> set){
        if(root == -1){
            return;
        }
        if(set.contains(root)){
            hasCircle = true;
            return;
        }
        set.add(root);
        search(leftChild[root], leftChild, rightChild, set);
        search(rightChild[root], leftChild, rightChild, set);
    }

    // 图论：叶子结点个数=非叶子结点个数+1
    // 将所有-1看成叶子结点，num(-1)=n+1
    public boolean validateBinaryTreeNodes2(int n, int[] leftChild, int[] rightChild) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += leftChild[i] == -1 ? 1 : 0;
            count += rightChild[i] == -1 ? 1 : 0;
        }
        return count == n + 1;
    }
}
