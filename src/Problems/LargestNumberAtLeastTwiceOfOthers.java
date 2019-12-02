package Problems;

/**
 *
 * In a given integer array nums, there is always exactly one largest element.
 * Find whether the largest element in the array is at least twice as much as every other number in the array.
 * If it is, return the index of the largest element, otherwise return -1.
 * 在一个给定的数组nums中，总是存在一个最大元素 。
 * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
 * 如果是，则返回最大元素的索引，否则返回-1。
 *
 */

public class LargestNumberAtLeastTwiceOfOthers {
    public int dominantIndex(int[] nums) {
        if(nums.length == 0){
            return -1;
        }
        if(nums.length == 1){
            return 0;
        }
        int fst = nums[0] > nums[1] ? 0 : 1;
        int sec = fst == 0 ? 1 : 0;
        for(int i = 2; i < nums.length; i++){
            if(nums[i] > nums[fst]){
                sec = fst;
                fst = i;
            }else if(nums[i] > nums[sec]){
                sec = i;
            }
        }
        return nums[fst] >= 2 * nums[sec] ? fst : -1;
    }
}
