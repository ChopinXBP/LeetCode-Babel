/**
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the minimum element.
 * The array may contain duplicates.
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 * 注意数组中可能存在重复的元素。
 *
 */

public class FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        int begin = 0;
        int end = nums.length - 1;
        while(begin < end){
            int mid = begin + ((end - begin) >> 1);
            if(nums[mid] < nums[begin]){
                end = mid;
            }else if(nums[end] < nums[begin]){
                begin = mid + 1;
            }
            //由于可能会出现重复元素，无法判断区间，例如[1,0,1,1,1]和[1,1,1,0,1]，因此采用end--来规避问题
            else{
                end--;
            }
        }
        return nums[begin];
    }
}
