package Problems;

/**
 *
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 *
 */

public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int begin = 0;
        int end = nums.length - 1;
        if(target < nums[begin]){
            return begin;
        }
        if(target > nums[end]){
            return end + 1;
        }
        while(begin < end){
            int mid = begin + ((end - begin) >> 1);
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                begin = mid + 1;
            }else {
                end = mid;
            }
        }
        return begin;
    }
}