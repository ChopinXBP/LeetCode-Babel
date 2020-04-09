package Problems;

import java.util.*;

public class FindModeInBinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    private int mode = 0;
    private HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    public int[] findMode(TreeNode root) {
        inorder(root);
        List<Integer> list = new ArrayList<Integer>();
        for(Integer i: map.keySet())
            if(mode==map.get(i))
                list.add(i);

        int[] result = new int[list.size()];
        Iterator<Integer> it = list.iterator();
        for(int i=0; i<result.length; i++)
            result[i] = it.next().intValue();

        return result;
    }

    public void inorder(TreeNode root){
        if(root==null)
            return;
        inorder(root.left);
        int m = map.getOrDefault(root.val,0) + 1;
        mode = Math.max(m, mode);
        map.put(root.val, m);
        inorder(root.right);
    }

}
