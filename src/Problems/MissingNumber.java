package Problems;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 */

public class MissingNumber {
    //交换：时间复杂度o(n)，空间复杂度o(1)
    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int nextLoc = nums[i];
            while (nextLoc != nums.length && nextLoc != nums[nextLoc]) {
                int temp = nums[nextLoc];
                nums[nextLoc] = nextLoc;
                nextLoc = temp;
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i){
                return i;
            }
        }
        return nums.length;
    }

    //异或：时间复杂度o(n)，空间复杂度o(1)
    public int missingNumber2(int[] nums) {
        //相同数字异或消除，留下单一数字
        int result = nums.length;
        for(int i = 0; i < nums.length; i++){
            result ^= i ^ nums[i];
        }
        return result;
    }

    //高斯求和：时间复杂度o(n)，空间复杂度o(1)
    public int missingNumber3(int[] nums) {
        int result = ((1 + nums.length) * nums.length) >> 1;
        for(int n : nums){
            result -= n;
        }
        return result;
    }
}
