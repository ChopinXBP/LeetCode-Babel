package Problems; /**
 * 
 * @author ChopinXBP
 * Given a collection of distinct integers, return all possible permutations.
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Permutations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1, 2, 3};
		List<List<Integer>> result = permute(nums);
		for(int i = 0; i < result.size(); i++) {
			for(int j = 0; j < result.get(i).size(); j++) {
				System.out.print(result.get(i).get(j) + " ");
			}
			System.out.println("");
		}
	}

    public static List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0) return result;
        
        Solution(nums, 0, result);
        
        return result;
    }
    
    public static void Solution(int[] nums, int loc, List<List<Integer>> result) {
    	if(loc == nums.length) {
        	//int[]转ArrayList
        	Integer[] newlist = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        	result.add(new ArrayList<>(Arrays.asList(newlist)));
    	}
    	
    	for(int i = loc; i < nums.length; i++) {
        	int temp = nums[loc];
        	nums[loc] = nums[i];
        	nums[i] = temp;
        	
    		Solution(nums, loc + 1, result);
    		
    		temp = nums[loc];
        	nums[loc] = nums[i];
        	nums[i] = temp;
    	}
    	    	
    }

}
