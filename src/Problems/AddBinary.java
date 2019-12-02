package Problems;

/**
 *
 * Given two binary strings, return their sum (also a binary string).
 * The input strings are both non-empty and contains only characters 1 or 0.
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * 输入为非空字符串且只包含数字 1 和 0。
 *
 */

public class AddBinary {

    public String addBinary(String a, String b) {
        //字符串可能过长
        long sum = Long.valueOf(a, 2) + Long.valueOf(b, 2);
        StringBuilder result = new StringBuilder();
        if(sum == 0){
            return result.append(0).toString();
        }
        while(sum != 0){
            result.insert(0, (sum & 1) == 1 ? 1 : 0);
            sum >>= 1;
        }
        return result.toString();
    }

    public String addBinary2(String a, String b) {
        int loca = a.length() - 1;
        int locb = b.length() - 1;
        if(loca < 0){
            return b;
        }
        if(locb < 0){
            return a;
        }
        StringBuilder result = new StringBuilder();
        int add = 0;
        while(loca >= 0 || locb >= 0){
            int sum = (loca >= 0 ? a.charAt(loca--) - '0' : 0) + (locb >= 0 ? b.charAt(locb--) - '0' : 0) + add;
            add = sum > 1 ? 1 : 0;
            result.insert(0, sum & 1);
        }
        if(add == 1){
            result.insert(0, 1);
        }
        return result.toString();
    }
}
