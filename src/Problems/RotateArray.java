package Problems;

/**
 *
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 */

public class RotateArray {

    //使用环状替换
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        int beginLoc = 0;
        int count = 0;
        //每次将一个元素放在其最终位置，如果k正好是n的倍数，会出现一次循环无法完成，需要两层循环，但实际是o(n)
        //利用计数器跳出外层循环，交换n次可以完成
        while(count < nums.length){
            int preNum = nums[beginLoc];
            int nextLoc = (beginLoc + k) % nums.length;
            while(nextLoc != beginLoc){
                int temp = nums[nextLoc];
                nums[nextLoc] = preNum;
                preNum = temp;
                nextLoc = (nextLoc + k) % nums.length;
                count++;
            }
            nums[nextLoc] = preNum;
            count++;
            beginLoc++;
        }
    }

    //三次翻转
    public void rotate2(int[] nums, int k){
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int begin, int end){
        while(begin < end){
            int temp = nums[begin];
            nums[begin++] = nums[end];
            nums[end--] = temp;
        }
    }
}
