package Problems;

/**
 *
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 */

public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        //二分查找最左元素
        int begin = 0;
        int end = nums.length;
        while(begin < end){
            int mid = (begin + end) >> 1;
            if(nums[mid] >= target){
                end = mid;
            }else{
                begin = mid + 1;
            }
        }

        int[] result = {-1, -1};
        if(begin == nums.length || nums[begin] != target){
            return result;
        }
        result[0] =  begin;

        //二分查找最右元素
        end = nums.length;
        while(begin < end){
            int mid = (begin + end) >> 1;
            if(nums[mid] > target){
                end = mid;
            }else{
                begin = mid + 1;
            }
        }
        result[1] = begin - 1;

        return result;
    }
}
