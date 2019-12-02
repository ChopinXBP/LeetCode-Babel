package Problems;

/**
 * 
 * @author ChopinXBP
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 
 */

public class SearchinRotatedSortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0)return -1;
        
        int begin = 0;
        int end = nums.length - 1;
        
        while(begin <= end) {
        	
        	int mid = (begin + end) >> 1;
        	if(nums[mid] == target) {
        		return mid;
        	}
        	//数组翻转
        	else if(nums[begin] > nums[end] && nums[begin] <= nums[mid]) {
        		if(nums[end] == target)
        			return end;
        		if(target < nums[end] || target > nums[mid]) {
        			begin = mid + 1;
        		}
        		else {
        			end = mid - 1;
        		}
        	}
        	else if(nums[begin] > nums[end] && nums[begin] > nums[mid]) {
        		if(nums[end] == target)
        			return end;
        		if(target < nums[end] && target > nums[mid]) {
        			begin = mid + 1;
        		}
        		else {
        			end = mid - 1;
        		}
        	}
        	//数组有序
        	else {
        		if(target > nums[mid]) {
        			begin = mid + 1;
        		}else {
        			end = mid - 1;
        		}
        	}
        }
        
        return -1;
    }
    

}
