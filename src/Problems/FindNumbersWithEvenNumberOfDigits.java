package Problems;

/**
 *
 * Given an array nums of integers, return how many of them contain an even number of digits.
 * 给你一个整数数组 nums，请你返回其中位数为 偶数 的数字的个数。
 *
 */

public class FindNumbersWithEvenNumberOfDigits {
    public int findNumbers(int[] nums) {
        int result = 0;
        for(int num : nums){
            if(((num + "").length() & 1) == 0){
                result++;
            }
        }
        return result;
    }
}
