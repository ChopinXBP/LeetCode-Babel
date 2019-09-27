import java.util.LinkedList;

/**
 *
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 */

public class SerializeAndDeserializeBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return "null";
        }
        StringBuilder result = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        result.append(root.val + ",");
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode curNode = queue.pollFirst();
                if(curNode.left == null){
                    result.append("null,");
                }else{
                    result.append(curNode.left.val + ",");
                    queue.add(curNode.left);
                }
                if(curNode.right == null){
                    result.append("null,");
                }else{
                    result.append(curNode.right.val + ",");
                    queue.add(curNode.right);
                }
            }
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("null")){
            return null;
        }
        String[] list = data.split(",");
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(list[0]));
        queue.add(root);
        int idx = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode curNode = queue.pollFirst();
                if(list[idx].equals("null")){
                    curNode.left = null;
                }else{
                    TreeNode newNode = new TreeNode(Integer.parseInt(list[idx]));
                    curNode.left = newNode;
                    queue.add(newNode);
                }
                idx++;
                if(list[idx].equals("null")){
                    curNode.right = null;
                }else{
                    TreeNode newNode = new TreeNode(Integer.parseInt(list[idx]));
                    curNode.right = newNode;
                    queue.add(newNode);
                }
                idx++;
            }
        }
        return root;
    }
}
