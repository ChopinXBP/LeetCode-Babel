/**
 * 
 * @author ChopinXBP
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * Note: The solution set must not contain duplicate triplets.
 * ����һ������ n ������������ nums���ж� nums ���Ƿ��������Ԫ�� a��b��c ��ʹ�� a + b + c = 0 ���ҳ��������������Ҳ��ظ�����Ԫ�顣
 * ע�⣺���в����԰����ظ�����Ԫ�顣
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

	//���������飬���������ÿһ��Ԫ��nums[i]����i֮����������Һ�Ϊ-nums[i]��������������֮�Ϳ�������β�ƽ�����⡣
    public static List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> result = new LinkedList<>();
    	if(nums == null || nums.length < 3) return result;
    	
    	Arrays.sort(nums);
    	
    	for(int i = 0; i < nums.length - 2; i++) {
    		//������ͬԪ��
    		if(i > 0 && nums[i] == nums[i - 1])
    			continue;
    		//�趨��βָ��ƽ�����
    		int begin = i + 1;
    		int end = nums.length - 1;
    		int num = -nums[i];
    		while(begin < end) {
    			if(nums[begin] + nums[end] == num) {
    				//���������ֳ�ʼ��ΪArrays������LinkedList3
    				//result.add(new ArrayList(Arrays.asList(num[i], num[begin], num[end])));
    				result.add(Arrays.asList(nums[i], nums[begin], nums[end]));
    				begin++;
    				end--;
    				//������ͬԪ��
    				while(begin < end && nums[begin] == nums[begin - 1]) {
    					begin++;
    				}
    				while(begin < end && nums[end] == nums[end + 1]) {
    					end--;
    				}
    			}
    			//����ӽ��С�ڸ�����beginǰ��
    			else if(nums[begin] + nums[end] < num) {
    				begin++;
    			}
    			//����ӽ�����ڸ�����end����
    			else {
    				end--;
    			}
    		}
    	}
    	
    	return result;
    }
}
