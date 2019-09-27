/**
 *
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place and use only constant extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 *
 */

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        //找出从右往左第一个比右边元素小的元素位置
        int firstDownLoc = nums.length - 2;
        while (firstDownLoc >= 0 && nums[firstDownLoc] >= nums[firstDownLoc + 1]){
            firstDownLoc--;
        }
        //如果数组正好逆序，则当前为最大排列，返回逆序数组
        if(firstDownLoc == -1){
            reverse(nums, 0);
            return;
        }
        //firstDownLoc左边的元素固定不变，将firstDownLoc右边第一个比其大的元素与其交换，再将右侧数组逆序，即可得到下一个排列。
        int firstSmallerThanLoc = nums.length - 1;
        while(firstSmallerThanLoc > firstDownLoc && nums[firstSmallerThanLoc] <= nums[firstDownLoc]){
            firstSmallerThanLoc--;
        }
        swap(nums, firstSmallerThanLoc, firstDownLoc);
        reverse(nums, firstDownLoc + 1);
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int begin){
        int end = nums.length - 1;
        while(begin < end){
            swap(nums, begin++, end--);
        }
    }
}
