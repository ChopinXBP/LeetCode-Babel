package Problems;

/**
 *
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 */

public class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int nextNum = nums[0];
        while(nums[nextNum] != -1){
            int temp = nums[nextNum];
            nums[nextNum] = -1;
            nextNum = temp;
        }
        return nextNum;
    }
}
