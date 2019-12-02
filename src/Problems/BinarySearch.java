package Problems;

/**
 *
 * Given a sorted (in ascending order) integer array nums of n elements and a target value,
 * write a function to search target in nums. If target exists, then return its index, otherwise return -1.
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 */

public class BinarySearch {
    public int search(int[] nums, int target) {
        int begin = 0;
        int end = nums.length - 1;
        while(begin <= end){
            int mid = (begin + end) >> 1;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                end = mid - 1;
            }else{
                begin = mid + 1;
            }
        }
        return -1;
    }
}
