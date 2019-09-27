/**
 * 
 * @author ChopinXBP
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * Note: The solution set must not contain duplicate triplets.
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {-1, 0, 1, 2, -1, -4};
		List<List<Integer>> result = threeSum(nums);
		for(int i = 0; i < result.size(); i++) {
			for(int j = 0; j < result.get(i).size(); j++) {
				System.out.print(result.get(i).get(j) + " ");
			}
			System.out.println(" ");
		}
	}

	//先排序数组，遍历数组的每一个元素nums[i]，在i之后的数组中找合为-nums[i]的两个数，两数之和可以用首尾逼近法求解。
    public static List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> result = new LinkedList<>();
    	if(nums == null || nums.length < 3) return result;
    	
    	Arrays.sort(nums);
    	
    	for(int i = 0; i < nums.length - 2; i++) {
    		//跳过相同元素
    		if(i > 0 && nums[i] == nums[i - 1])
    			continue;
    		//设定首尾指针逼近查找
    		int begin = i + 1;
    		int end = nums.length - 1;
    		int num = -nums[i];
    		while(begin < end) {
    			if(nums[begin] + nums[end] == num) {
    				//将三个数字初始化为Arrays并存入LinkedList3
    				//result.add(new ArrayList(Arrays.asList(num[i], num[begin], num[end])));
    				result.add(Arrays.asList(nums[i], nums[begin], nums[end]));
    				begin++;
    				end--;
    				//跳过相同元素
    				while(begin < end && nums[begin] == nums[begin - 1]) {
    					begin++;
    				}
    				while(begin < end && nums[end] == nums[end + 1]) {
    					end--;
    				}
    			}
    			//若相加结果小于该数，begin前进
    			else if(nums[begin] + nums[end] < num) {
    				begin++;
    			}
    			//若相加结果大于该数，end后退
    			else {
    				end--;
    			}
    		}
    	}
    	
    	return result;
    }
}
