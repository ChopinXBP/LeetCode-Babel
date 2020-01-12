package Problems;

/**
 *
 * Given an integer n. No-Zero integer is a positive integer which doesn't contain any 0 in its decimal representation.
 * Return a list of two integers [A, B] where:
 * A and B are No-Zero integers.
 * A + B = n
 * It's guarateed that there is at least one valid solution. If there are many valid solutions you can return any of them.
 * 给你一个整数 n，请你返回一个 由两个整数组成的列表 [A, B]，满足：
 * A 和 B 都是无零整数
 * A + B = n
 * 题目数据保证至少有一个有效的解决方案。
 * 如果存在多个有效解决方案，你可以返回其中任意一个。
 *
 */

public class ConvertIntegerToTheSumOfTwoNoZeroIntegers {
    public int[] getNoZeroIntegers(int n) {
        for (int i = 1; i < n; i++) {
            if (isNoZeroInt(i) && isNoZeroInt(n - i)) {
                return new int[]{i, n - i};
            }
        }
        return null;
    }

    private boolean isNoZeroInt(int n){
        while (n > 0) {
            if (n % 10 == 0) {
                return false;
            }
            n /= 10;
        }
        return true;
    }
}
