package Problems;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 */

public class ConvertSortedArrayToBinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //排序数组转二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {
        return Solution(nums, 0, nums.length - 1);
    }

    public TreeNode Solution(int[] nums, int begin, int end) {
        if (begin > end) {
            return null;
        }
        //使用int mid = begin + (begin - end) >> 1;
        //使用int mid = (begin + end + 1) >> 1;可以得到另一种构型
        int mid = (begin + end) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = Solution(nums, begin, mid - 1);
        root.right = Solution(nums, mid + 1, end);
        return root;
    }

    //排序链表转二叉搜索树
    //快慢指针法
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        //快慢指针求中点
        ListNode begin = new ListNode(0);
        begin.next = head;
        ListNode fast = head;
        ListNode low = begin;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            low = low.next;
        }
        TreeNode root = new TreeNode(low.next.val);

        //区间断裂
        root.right = sortedListToBST(low.next.next);
        low.next = null;
        root.left = sortedListToBST(begin.next);

        return root;
    }

    //中序遍历法（最优）：根据中序遍历顺序重构二叉树
    public ListNode list;
    public TreeNode sortedListToBST2(ListNode head){
        list = head;
        int len = -1;
        while(head != null){
            head = head.next;
            ++len;
        }
        return Solution2(0, len);
    }

    public TreeNode Solution2(int begin, int end){
        if(begin > end){
            return null;
        }

        int mid = (begin + end) >> 1;
        TreeNode left = Solution2(begin, mid - 1);
        TreeNode node = new TreeNode(list.val);
        node.left = left;
        list = list.next;
        node.right = Solution2(mid + 1, end);

        return node;
    }
}
