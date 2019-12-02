package Problems;

/**
 *
 * Given an array of n positive integers and a positive integer s,
 * find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 *
 */

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        int begin = 0;
        int end = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        while(end < nums.length){
            sum += nums[end];
            if(sum >= s){
                while(begin < end && sum - nums[begin] >= s){
                    sum -= nums[begin];
                    begin++;
                }
                result = Math.min(result, end - begin + 1);
            }
            end++;
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
