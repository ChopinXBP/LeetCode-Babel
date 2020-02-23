package Problems;

/**
 *
 * Given an integer num, find the closest two integers in absolute difference whose product equals num + 1 or num + 2.
 * Return the two integers in any order.
 * 给你一个整数 num，请你找出同时满足下面全部要求的两个整数：
 * 两数乘积等于  num + 1 或 num + 2
 * 以绝对差进行度量，两数大小最接近
 * 你可以按任意顺序返回这两个整数。
 *
 */

public class ClosestDivisors {
    public int[] closestDivisors(int num) {
        int begin = 2;
        int end = (num >> 1) + 1;
        int[] result = {begin, end};
        while(begin < end){
            begin++;
            if((num + 1) % begin == 0){
                end = (num + 1) / begin;
                result[0] = begin;
                result[1] = end;
                continue;
            }
            if((num + 2) % begin == 0){
                end = (num + 2) / begin;
                result[0] = begin;
                result[1] = end;
                continue;
            }
            end = (num + 2) / begin;
        }
        return result;
    }
}
