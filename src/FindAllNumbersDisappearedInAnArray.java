import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 */

public class FindAllNumbersDisappearedInAnArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            int nextLoc = nums[i] - 1;
            while(nextLoc != nums[nextLoc] - 1){
                int temp = nums[nextLoc] - 1;
                nums[nextLoc] = nextLoc + 1;
                nextLoc = temp;
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i + 1){
                result.add(i + 1);
            }
        }
        return result;
    }
}
