/**
 *
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * Note: Do not use any built-in library function such as sqrt.
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 * 说明：不要使用任何内置的库函数，如  sqrt。
 *
 */

public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        int begin = 1;
        int end = 46340;
        while(begin <= end){
            int mid = (begin + end) >> 1;
            if(mid * mid == num){
                return true;
            }else if(mid * mid < num){
                begin = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return false;
    }
}
