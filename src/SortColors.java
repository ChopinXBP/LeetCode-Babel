/**
 *
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * You are not suppose to use the library's sort function for this problem.
 * Could you come up with a one-pass algorithm using only constant space?
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 不能使用代码库中的排序函数来解决这道题。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 */

public class SortColors {
    //三指针法：移动指针i，当i小于twoflag时，与twoflag交换所有遇到的2;当i大于zeroflag时，与zeroflag交换所有遇到的0
    public void sortColors(int[] nums) {
        int zeroflag = 0;
        int twoflag = nums.length - 1;
        for(int i = 0; i <= twoflag; i++){
            while(nums[i] == 2 && i < twoflag){
                int temp = nums[i];
                nums[i] = nums[twoflag];
                nums[twoflag--] = temp;
            }
            while(nums[i] == 0 && i > zeroflag){
                int temp = nums[i];
                nums[i] = nums[zeroflag];
                nums[zeroflag++] = temp;
            }
        }
    }
}
