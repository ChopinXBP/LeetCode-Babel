/**
 * 
 * @author ChopinXBP
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 
 */

public class RemoveDuplicatesfromSortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,1,1,2};
		int[] nums2 = {1,1,1};
		int len = removeDuplicates(nums);
		for (int i = 0; i < len; i++) {
		    System.out.print(nums[i] + " ");
		}
		System.out.println(" ");
		int len2 = removeDuplicates(nums2);
		for (int i = 0; i < len2; i++) {
		    System.out.print(nums2[i] + " ");
		}	
	}

    public static int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0)
        	return 0;
        	
        int i = 0;
        for(int j = 1 ; j < nums.length; j++) {
        	if(nums[j] != nums[i]) {
        		nums[++i] = nums[j];
        	}
        }
        
        return i + 1;
    }
}
