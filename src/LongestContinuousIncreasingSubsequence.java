import java.util.LinkedList;

/**
 *
 * Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).
 * 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
 *
 */

public class LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int result = 1;
        int begin = 0;
        int end = 1;
        while(end < nums.length){
            while(end < nums.length && nums[end] > nums[end - 1]){
                end++;
            }
            result = Math.max(end - begin, result);
            begin = end;
            end++;
        }
        return result;
    }
}
