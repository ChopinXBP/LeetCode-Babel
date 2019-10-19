/**
 *
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 *
 */

public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int idx = 0;
        int result = 0;
        while(idx < nums.length){
            if(nums[idx] == 0){
                idx++;
                continue;
            }
            int begin = idx;
            while(idx < nums.length && nums[idx] == 1){
                idx++;
            }
            result = Math.max(result, idx - begin);
        }
        return result;
    }
}
