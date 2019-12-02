package Problems;

/**
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 * 你可以假设数组中不存在重复元素。
 *
 */

public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int begin = 0;
        int end = nums.length - 1;
        while(begin < end){
            int mid = begin + ((end - begin) >> 1);
            if(nums[mid] < nums[begin]){
                end = mid;
            }else if(nums[end] <= nums[begin]){
                begin = mid + 1;
            }else{
                end = mid;
            }
        }
        return nums[begin];
    }
}
