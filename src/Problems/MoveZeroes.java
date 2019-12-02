package Problems;

import java.util.Arrays;

/**
 *
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 */

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int low = 0;
        int fast = 0;
        while(fast < nums.length){
            if(nums[fast] != 0){
                if(fast != low){
                    int temp = nums[fast];
                    nums[fast] = nums[low];
                    nums[low] = temp;
                }
                low++;
            }
            fast++;
        }
        Arrays.fill(nums, low, nums.length, 0);
    }
}
