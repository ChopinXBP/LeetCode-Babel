package Problems;

import java.util.*;

/**
 *
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 */

public class CountOfSmallerNumbersAfterSelf {
    //归并排序+索引数组（同理可以求左侧大于/小于当前元素的个数）
    public List<Integer> countSmaller(int[] nums) {
        int[] numsIdx = new int[nums.length];
        Integer[] result = new Integer[nums.length];
        for(int i = 0; i < numsIdx.length; i++){
            numsIdx[i] = i;
        }
        Solution(nums, 0, nums.length - 1, numsIdx, result);
        return Arrays.asList(result);
    }

    public void Solution(int[] nums, int begin, int end, int[] numsIdx, Integer[] list){
        if(begin >= end){
            return;
        }
        int mid = (begin + end) >> 1;
        Solution(nums, begin, mid, numsIdx, list);
        Solution(nums, mid + 1, end, numsIdx, list);

        int leftIdx = begin;
        int rightIdx = mid + 1;
        int[] result = new int[end - begin + 1];
        int resultIdx = 0;
        while(leftIdx <= mid && rightIdx <= end){
            //当位于左数组的元素i出列时候，右数组已经出列的元素均比其小，累加元素个数
            if(nums[numsIdx[leftIdx]] <= nums[numsIdx[rightIdx]]){
                int idx = numsIdx[leftIdx++];
                result[resultIdx++] = idx;
                list[idx] += rightIdx - (mid + 1);
            }else{
                result[resultIdx++] = numsIdx[rightIdx++];
            }
        }
        if(leftIdx > mid){
            System.arraycopy(numsIdx, rightIdx, result, resultIdx, end - rightIdx + 1);
        }else{
            //当位于左数组的元素i出列时候，右数组已经出列的元素均比其小，累加元素个数
            while(leftIdx <= mid){
                int idx = numsIdx[leftIdx++];
                result[resultIdx++] = idx;
                list[idx] += rightIdx - (mid + 1);
            }
        }
        System.arraycopy(result, 0, numsIdx, begin, end - begin + 1);
    }

    //二叉搜索树
    //构建一棵深度为n的二叉树，将数组元素从后往前依次插入，每一个小于当前元素i的右侧元素个数=插入过程经过的结点数+经过结点左子树的结点总数
    class Node{
        Node left, right;
        int val, leftNodeSums, sameNodeSums = 1;
        public Node(int v, int s){
            val = v;
            leftNodeSums = s;
        }
    }
    public List<Integer> countSmaller2(int[] nums){
        Integer[] result = new Integer[nums.length];
        Node root = null;
        for(int i = nums.length - 1; i >= 0; i--){
            root = insert(nums[i], root, result, i, 0);
        }
        return Arrays.asList(result);
    }

    public Node insert(int num, Node node, Integer[] res, int idx, int preSum){
        if(node == null){
            node = new Node(num, 0);
            res[idx] = preSum;
        }
        else if(num == node.val){
            node.sameNodeSums++;
            res[idx] = preSum + node.leftNodeSums;
        }
        else if(num < node.val){
            node.leftNodeSums++;
            node.left = insert(num, node.left, res, idx, preSum);
        }
        //当前元素num小于当前结点node时，递归插入右子树，并更新次数preSum（经过的结点数+结点左子树的结点总数）
        else{
            node.right = insert(num, node.right, res, idx, preSum + node.sameNodeSums + node.leftNodeSums);
        }
        return node;
    }
}
