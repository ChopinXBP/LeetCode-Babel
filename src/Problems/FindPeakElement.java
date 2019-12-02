package Problems;

/**
 *
 * A peak element is an element that is greater than its neighbors.
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that nums[-1] = nums[n] = -∞.
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 *
 */

public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int begin = 0;
        int end = nums.length - 1;
        while(begin < end){
            int mid = (begin + end) >> 1;
            if(nums[mid] > nums[mid + 1]){
                end = mid;
            }else{
                begin = mid + 1;
            }
        }
        return end;
    }
}
