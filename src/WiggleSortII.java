import java.util.Arrays;

/**
 *
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * 给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 *
 */

public class WiggleSortII {
    //排序数组，降序穿插（小数在后的顺序对）。例如[1,2,4,4,4,6]，穿插成[4,6,2,4,1,4]
    public void wiggleSort(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        int minIdx = (nums.length & 1) == 1 ? nums.length >> 1 : (nums.length >> 1) - 1;
        int maxIdx = nums.length - 1;
        for(int i = 0; i < nums.length - 1; i+=2){
            nums[i] = copy[minIdx--];
            nums[i + 1] = copy[maxIdx--];
        }
        if((nums.length & 1) == 1){
            nums[nums.length - 1] = copy[minIdx--];
        }
    }
}
