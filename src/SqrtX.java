/**
 * Implement int sqrt(int x).
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 */

public class SqrtX {
    //自上而下
    public int mySqrt(int x) {
        for (long i = 0; i <= x; i++) {
            if (i * i > x) {
                return (int) i - 1;
            }
        }
        return x;
    }

    //二分查找
    public int mySqrt1(int x) {
        //x的平方根不会大于(x >> 1) + 1
        long begin = 0;
        long end = (x >> 1) + 1;
        while (begin <= end) {
            long mid = (begin + end) >> 1;
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid < x) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return (int) end;
    }

    //牛顿迭代法int版本
    public double mySqrt2(double x){
        if(x < 2){
            return x;
        }
        double cur = 1.0;
        double last = 0;
        //收敛判断方法：前后两个解是否无限接近
        while (Math.abs(last - cur) > 1e-9) {
            last = cur;
            cur = (cur + x / cur) / 2;
        }
        return (int)cur;
    }

    //牛顿迭代法double版本()
    public double mySqrt3(double x) {
        double k = 1.0;
        //收敛判断方法：解是否无限趋向0
        while (Math.abs(k * k - x) > 1e-9) {
            k = (k + x / k) / 2;
        }
        return k;
    }
}
