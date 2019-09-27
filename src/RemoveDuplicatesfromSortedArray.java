/**
 * 
 * @author ChopinXBP
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * ����һ���������飬����Ҫ��ԭ��ɾ���ظ����ֵ�Ԫ�أ�ʹ��ÿ��Ԫ��ֻ����һ�Σ������Ƴ���������³��ȡ�
 * ��Ҫʹ�ö��������ռ䣬�������ԭ���޸��������鲢��ʹ�� O(1) ����ռ����������ɡ�
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
