package Problems;

/**
 *
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 */

public class AddStrings {
    public String addStrings(String num1, String num2) {
        int idx1 = num1.length() - 1;
        int idx2 = num2.length() - 1;
        StringBuilder result = new StringBuilder();
        int add = 0;
        while(idx1 >= 0 && idx2 >= 0){
            int n1 = num1.charAt(idx1--) - '0';
            int n2 = num2.charAt(idx2--) - '0';
            int num = n1 + n2 + add;
            if(num > 9){
                add = 1;
                num -= 10;
            }else{
                add = 0;
            }
            result.insert(0, num);
        }
        int idx = idx1 >= 0 ? idx1 : idx2;
        String cur = idx1 >= 0 ? num1 : num2;
        while(idx >= 0){
            int n = cur.charAt(idx--) - '0';
            int num = n + add;
            add = 0;
            if(num > 9){
                add = 1;
                num -= 10;
            }else{
                add = 0;
            }
            result.insert(0, num);
        }
        if(add == 1){
            result.insert(0, 1);
        }
        return result.toString();
    }
}
