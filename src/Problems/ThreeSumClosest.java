package Problems; /**
 * 
 * @author ChopinXBP
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 
 */

import java.util.Arrays;

public class ThreeSumClosest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {-1, 2, 1, -4};
		int[] nums2 = {1, 1, -1};
		System.out.println(threeSumClosest(nums, 1));
		System.out.println(threeSumClosest(nums2, 1));
	}

    public static int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length < 3) return 0;
        
        Arrays.sort(nums);
        
        int distance = Integer.MAX_VALUE;
        int result = 0;
        for(int i = 0; i < nums.length - 2; i++) {
        	if(i > 0 && nums[i] == nums[i - 1])
        		continue;
        	int begin = i + 1;
        	int end = nums.length - 1;
        	int sum = target - nums[i];
        	while(begin < end) {
            	if(nums[begin] + nums[end] == sum) {
            		return target;
            	}
            	else if(nums[begin] + nums[end] > sum) {
            		if(Math.abs(nums[begin] + nums[end] - sum) < distance) {
            			distance = Math.abs(nums[begin] + nums[end] - sum);
            			result = nums[begin] + nums[end] + nums[i];
            		}
            		end--;
            	}
            	else {
            		if(Math.abs(nums[begin] + nums[end] - sum) < distance) {
            			distance = Math.abs(nums[begin] + nums[end] - sum);
            			result = nums[begin] + nums[end] + nums[i];
            		}
            		begin++;
            	}
        	}
        }
        	
        return result;
    }
}
