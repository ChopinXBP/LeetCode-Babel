package Problems;

/**
 *
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 */

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int result = nums[0];
        int max = nums[0];
        int min = nums[0];

        int idx = 1;
        while(idx < nums.length){
            //存在复数时将最大值和最小值交换
            if(nums[idx] < 0){
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(nums[idx], max * nums[idx]);
            min = Math.min(nums[idx], min * nums[idx]);
            result = result > max ? result : max;
            idx++;
        }

        return result;
    }
}
