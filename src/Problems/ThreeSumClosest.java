package Problems; /**
 * 
 * @author ChopinXBP
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * ����һ������ n ������������ nums �� һ��Ŀ��ֵ target���ҳ� nums �е�����������ʹ�����ǵĺ��� target ��ӽ����������������ĺ͡��ٶ�ÿ������ֻ����Ψһ�𰸡�
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
