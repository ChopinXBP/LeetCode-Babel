package Problems;

import java.util.ArrayList;

/**
 *
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * 编写一个程序，找出第 n 个丑数。
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 *
 */

public class UglyNumberII {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        for (int i = 1; i < n; i++) {
            //相当于三路归并
            int factor2 = 2 * ugly[index2];
            int factor3 = 3 * ugly[index3];
            int factor5 = 5 * ugly[index5];
            int min = Math.min(Math.min(factor2, factor3), factor5);
            ugly[i] = min;
            //找出三个指针的最小元素，将指针后移
            //如果有多组指针的当前值都是 min，指针都需要后移，保证 ugly 数组中不会加入重复元素。
            if (factor2 == min)
                index2++;
            if (factor3 == min)
                index3++;
            if (factor5 == min)
                index5++;
        }
        return ugly[n - 1];
    }
}
