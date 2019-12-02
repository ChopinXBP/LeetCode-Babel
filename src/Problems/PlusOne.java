package Problems;

import java.util.Arrays;

/**
 *
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 */

public class PlusOne {
    public int[] plusOne(int[] digits) {
        int idx = digits.length - 1;
        while(idx >= 0){
            if(++digits[idx] < 10){
                return digits;
            }
            digits[idx--] = 0;
        }
        //数组向前扩容并保留原数组
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
        //等同于
        //int[] result = new int[digits.length + 1];
        //result[0] = 1;
        //System.arraycopy(digits, 0, result, 1, digits.length);
        //return result;
    }
}
