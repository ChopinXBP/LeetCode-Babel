package Problems;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * Write a program to check whether a given number is an ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * 编写一个程序判断给定的数是否为丑数。
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 *
 */

public class UglyNumber {
    public boolean isUgly(int num) {
        if (num < 1){
            return false;
        }
        while (num % 5 == 0){
            num /= 5;
        }
        while (num % 3 == 0){
            num /= 3;
        }
        while ((num & 1) == 0){
            num >>= 1;
        }
        return num == 1;
    }
}
