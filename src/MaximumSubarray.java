/**
 * 
 * @author ChopinXBP
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * ����һ���������� nums ���ҵ�һ���������͵����������飨���������ٰ���һ��Ԫ�أ������������͡�
 *
 */

public class MaximumSubarray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {-2 , 1};
		int[] nums2 = {-2 , -1};
		System.out.println(maxSubArray(nums));
		System.out.println(maxSubArray(nums2));
	}
	
	//���ߴ���
	public static int maxSubArray(int[] nums) {
    	int result = nums[0];
    	int maxsubSum = 0;
    	for(int i = 0; i < nums.length; i++) {
    		maxsubSum += nums[i];
    		if(maxsubSum > result) {
    			result = maxsubSum;
    		}
    		if(maxsubSum < 0) {
    			maxsubSum = 0;
    		}
    	}
    	return result;
    }
		
	//�����㷨
    public static int maxSubArray2(int[] nums) {
    	return Solution(nums, 0, nums.length - 1);
    }
    
    public static int MaxofThree(int a, int b, int c) {
    	return a > b ? (a > c ? a : c) : (b > c ? b : c);
    }
    
    public static int Solution(int[] nums, int begin, int end) {
    	if(begin == end) {
    		return nums[begin];
    	}
    	
    	int mid = (begin + end) >> 1;
    	int maxleftNum = Solution(nums, begin, mid);		//�ݹ��������е�������к�
    	int maxrightNum = Solution(nums, mid + 1, end);		//�ݹ��������е�������к�
    	
    	int leftSum = 0, maxmidleft = nums[mid];
    	for(int i = mid; i >= begin; i--) {
    		leftSum += nums[i];
    		if(leftSum > maxmidleft) {
    			maxmidleft = leftSum;
    		}
    	}
    	int rightSum = 0, maxmidright = nums[mid + 1];
    	for(int i = mid + 1; i <= end; i++) {
    		rightSum += nums[i];
    		if(rightSum > maxmidright) {
    			maxmidright = rightSum;
    		}
    	}
    	
    	return MaxofThree(maxleftNum, maxrightNum, maxmidleft + maxmidright);
    	
    }
}
