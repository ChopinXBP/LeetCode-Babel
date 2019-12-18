package Problems;

/**
 *
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 *
 */

public class AddDigits {
    //数字为0时，结果为0；数字为9的倍数时，结果为9；其他情况下为除以9的余数。
    public int addDigits(int num) {
        //到9的时候就不能再计算了 所以余数为0为9
        return num == 0 ? 0 : ((num % 9 == 0) ? 9 : num % 9);
    }
}
