package Problems;

/**
 *
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 * Given n, find the total number of full staircase rows that can be formed.
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 * 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
 * 给定一个数字 n，找出可形成完整阶梯行的总行数。
 * n 是一个非负整数，并且在32位有符号整型的范围内。
 *
 */

public class ArrangingCoins {
    public int arrangeCoins(int n) {
        int begin = 0;
        int end = n;
        while (begin < end){
            long mid = begin + ((end - begin) >> 1);
            long sum = mid * (1 + mid) >> 1;
            if(sum <= n && n - sum < mid + 1){
                return (int)mid;
            }else if(sum < n){
                begin = (int)mid + 1;
            }else{
                end = (int)mid - 1;
            }
        }
        return begin;
    }

    //根据数学公式，k(k+1) /2 = n，可以得到其正数解为：k = sqrt(2n+1/4) - 1/2。然后求整即可。
    //这里2n+1/4有可能会超出sqrt函数的参数范围。可以变换一下， k = sqrt(2) * sqrt(n+1/8) - 1/2，这样求平方根就不会超限了。
    public int arrangeCoins2(int n) {
        return (int)(Math.sqrt(2) * Math.sqrt(n + 0.125) - 0.5);
    }
}
