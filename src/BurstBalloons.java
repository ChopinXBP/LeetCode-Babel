import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * Note:
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。
 * 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 * 求所能获得硬币的最大数量。
 * 说明:
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 */

//https://www.bilibili.com/video/av45180542
public class BurstBalloons {

    public static void main(String[] args){
        int[] nums = {7,9,8,0,7,1,3,5,5,2,3};
        System.out.println(maxCoins2(nums));
    }

    //回溯法（会超时）
    public int score;
    public int maxCoins(int[] nums) {
        if(nums.length == 0)
            return 0;
        score = 0;
        Solution(nums, 0);
        return score;
    }

    public void Solution(int[] nums, int coins){
        if(nums.length == 0){
            if(coins > score){
                score = coins;
            }
            return;
        }

        for(int i = 0; i < nums.length; i++){
            int delta = nums[i] * (i - 1 < 0 ? 1 : nums[i - 1]) * (i + 1 > nums.length - 1 ? 1 : nums[i + 1]);
            int[] newnum = new int[nums.length - 1];
            System.arraycopy(nums, 0, newnum, 0, i);
            System.arraycopy(nums, i + 1, newnum, i, nums.length - i - 1);
            Solution(newnum, coins + delta);
        }
    }

    //动态规划
    //逆向思考：拿出一个气球作为最后一个点爆的气球，则该气球左边和右边的气球分别是两个独立的子问题
    //拓展到某个区间[begin, end]，对该区间内的所有气球i进行遍历，计算每一个气球作为最后一个气球时，区间能够获得硬币的最大数量
    //最后一个气球时获得硬币的数量 = 左区间最大数量 + 右区间最大数量 + nums[i] * nums[begin-1] * nums[end+1]（因为计算i时，区间内已经没有气球，取区间两端）
    //将遍历所有气球后的最大值更新到dp[begin][end],表示该区间从第begin个气球到第end个气球中能够获得硬币的最大数量
    public static int[][] dp;

    public static int maxCoins1(int[] nums) {
        if(nums.length == 0)
            return 0;

        //将nums转换为ArrayList，并在前后位置1
        Integer[] intnum = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        ArrayList<Integer> numslist = new ArrayList<>(Arrays.asList(intnum));
        numslist.add(0, 1);
        numslist.add(1);

        //将dp初始化为（n+2）*（n+2）的数组
        dp = new int[nums.length + 2][nums.length + 2];


        return Solution1(numslist, 1, nums.length);
    }

    public static int Solution1(ArrayList<Integer> numslist, int begin, int end){
        if(begin > end)
            return 0;
        //不对已更新的区间进行二次更新
        if(dp[begin][end] > 0)
            return dp[begin][end];

        //dp[begin][end]是从第begin个气球到第end个气球中能够获得硬币的最大数量
        //dp[begin][end] = dp[begin][loc - 1] + dp[loc + 1][end] + nums[begin - 1] * nums[loc] * nums[end + 1](begin <= loc <= end)
        for(int i = begin; i <= end; i++){
            int left = Solution1(numslist, begin, i - 1);
            int right = Solution1(numslist, i + 1, end);
            int delta = numslist.get(i) * numslist.get(begin - 1) * numslist.get(end + 1);
            dp[begin][end] = Math.max(dp[begin][end], left + right + delta);
        }

        return dp[begin][end];
    }

    //动态规划改进版
    //自底向上思考，先计算每个气球单独的情况，再拓展区间，避免递归与重复计算dpscore值
    public static int maxCoins2(int[] nums){
        int size = nums.length;
        if(size <= 0)
            return 0;
        int[][] dpscore = new int[size + 2][size + 2];

        Integer[] intnums = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(intnums));
        list.add(0, 1);
        list.add(1);

        //最外层循环（大区间）：从区间长度len = 1开始更新，到区间长度为全长
        for(int len = 1; len <= size; ++len){
            //中间层循环（大区间右侧固定的子区间）：从区间起点begin=1开始更新，到区间长度len，对应区间终点end为begin+len-1。
            //由此需要end<=size对应最后一个气球
            for(int begin = 1; begin + len - 1 <= size; ++begin){
                int end = begin + len - 1;
                //在区间[begin， end]内更新每一个dpscore值
                for(int loc = begin; loc <= end; ++loc) {
                    dpscore[begin][end] = Math.max(dpscore[begin][end], dpscore[begin][loc - 1] + dpscore[loc + 1][end] + list.get(loc) * list.get(begin - 1) * list.get(end + 1));
                }
            }
        }

        return dpscore[1][size];
    }
}
