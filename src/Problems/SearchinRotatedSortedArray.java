package Problems;

/**
 * 
 * @author ChopinXBP
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * ���谴�����������������Ԥ��δ֪��ĳ�����Ͻ�������ת��
 * ( ���磬���� [0,1,2,4,5,6,7] ���ܱ�Ϊ [4,5,6,7,0,1,2] )��
 * ����һ��������Ŀ��ֵ����������д������Ŀ��ֵ���򷵻��������������򷵻� -1 ��
 * ����Լ��������в������ظ���Ԫ�ء�
 * ����㷨ʱ�临�Ӷȱ����� O(log n) ����
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
        	//���鷭ת
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
        	//��������
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
