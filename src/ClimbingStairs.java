/**
 *
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 *
 */

public class ClimbingStairs {
    public int climbStairs(int n) {
        int fast = 1;
        int slow = 0;
        while(n > 1){
            slow = fast - slow;
            fast = slow + fast;
            n--;
        }
        return fast;
    }
}
