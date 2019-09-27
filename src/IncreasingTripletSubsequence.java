/**
 *
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * Formally the function should:
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * 给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。
 * 数学表达式如下:
 * 如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
 * 使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。
 *
 */

public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        if(nums.length < 3){
            return false;
        }
        //min表示当前遇到的最小值，mid表示当前遇到的较小值
        //不断迭代替换，最终序列不一定是上升子序列，当一定能够证明满足条件
        int min = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for(int num : nums){
            if(num <= min){
                min = num;
            }else if(num <= mid){
                mid = num;
            }else{
                return true;
            }
        }
        return false;
    }
}
