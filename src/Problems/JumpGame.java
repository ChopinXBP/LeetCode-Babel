package Problems;

/**
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 *
 */

public class JumpGame {

    // 设定一个变量用来存储最远可达距离maxdist，从前往后每当走到每一个位置的时候，更新这个距离。
    // 如果maxdist能够到达最后一位，则游戏通过,返回true；如果不能到达，则游戏失败，返回false。
    public boolean canJump(int[] nums) {
        int maxdist = 0;
        for(int i = 0; i <= maxdist; i++){
            int curdist = i + nums[i];
            if(curdist >= nums.length - 1) {
                return true;
            }
            maxdist = curdist > maxdist ? curdist : maxdist;
        }
        return false;
    }

    // 设定一个标志位代表最后一位lastPos，从后往前每当一个新的位置能够到达尾部，则将lastPos更新至该位置。
    // 如果lastPos能够到达首位，则游戏通过，返回true；如果不能到达，则游戏失败，返回false。
    public boolean canJump2(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
}