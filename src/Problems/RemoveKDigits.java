package Problems;

import java.util.LinkedList;

/**
 *
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is
 * the smallest possible.
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 注意:
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 *
 */

public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        LinkedList<Character> list = new LinkedList<>();
        for (char c : num.toCharArray()) {
            if(list.isEmpty() || c >= list.getLast()){
                list.add(c);
                continue;
            }
            while(!list.isEmpty() && c < list.getLast() && k > 0){
                list.pollLast();
                k--;
            }
            list.add(c);
        }
        while(k > 0){
            list.pollLast();
            k--;
        }
        StringBuilder result = new StringBuilder();
        boolean begin = true;
        for(char c : list){
            if(begin && c == '0'){
                continue;
            }
            begin = false;
            result.append(c);
        }
        return result.length() == 0 ? "0" : result.toString();
    }
}
