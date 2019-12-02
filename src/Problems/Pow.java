package Problems;

/**
 *
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 */

public class Pow {

    //递归法
    public double myPow(double x, int n) {
        if(x == 0){
            return -1;
        }
        if(n == 0){
            return 1;
        }
        if(n == Integer.MIN_VALUE){
            return x == -1 || x == 1 ? 1 : 0;
        }
        if(n < 0){
            x = 1 / x;
            n = -n;
        }
        return (n & 0x01) == 1 ? x * myPow(x * x, n >> 1) : myPow(x * x, n >> 1);
    }

    //循环法
    public double myPow2(double x, int n){
        if(x == 0){
            return -1;
        }
        if(n < 0){
            x = 1 / x;
            n = -n;
        }
        double result = 1.0;
        while(n != 0){
            if((n & 0x01) == 1){
                result *= x;
            }
            x *= x;
            n /= 2;
        }
        return result;
    }
}
