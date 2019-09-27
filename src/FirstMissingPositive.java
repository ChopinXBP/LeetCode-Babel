/**
 *
 * Given an unsorted integer array, find the smallest missing positive integer.
 * Your algorithm should run in O(n) time and uses constant extra space.
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 *
 */

public class FirstMissingPositive {
    //第一个未出现的正整数必定在1至nums.length+1之中，因此利用桶排序的思想借助给定数组记录每一个出现过的值
    public int firstMissingPositive(int[] nums) {
        //第一次循环，将每一个在1至num.length中的数num放入对应桶nums[num-1]中
        for(int i = 0; i < nums.length; i++){
            int curnum = nums[i];
            while(curnum > 0 && curnum <= nums.length && nums[curnum - 1] != curnum){
                int newnum = nums[curnum - 1];
                nums[curnum - 1] = curnum;
                curnum = newnum;
            }
        }
        //第二次循环，将第一个和桶不对应的数字位置+1进行输出，若数组全对应，则输出nums.length+1
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i + 1){
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
