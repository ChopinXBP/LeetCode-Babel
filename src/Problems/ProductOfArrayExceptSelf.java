package Problems;

/**
 *
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 */

public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];

        up[0] = 1;
        for(int i = 1; i < nums.length; i++){
            up[i] = up[i - 1] * nums[i - 1];
        }

        down[nums.length - 1] = 1;
        for(int i = nums.length - 2; i >= 0; i--){
            down[i] = down[i + 1] * nums[i + 1];
        }

        for(int i = 0; i < nums.length; i++){
            up[i] = up[i] * down[i];
        }

        return up;
    }

}
