package Problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 * 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
 * 说明: 输入可能包含了除 ( 和 ) 以外的字符。
 *
 */

public class RemoveInvalidParentheses {
    //回溯法
    private HashSet<String> result = new HashSet<>();
    public List<String> removeInvalidParentheses(String s) {
        //计算不匹配的左右括号数leftError、rightError
        int leftError = 0;
        int rightError = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                leftError++;
            }else if(s.charAt(i) == ')'){
                if(leftError == 0){
                    rightError++;
                }else{
                    leftError--;
                }
            }
        }
        Solution(s, 0, 0, 0, leftError, rightError, new StringBuilder());
        return new ArrayList<>(result);
    }

    private void Solution(String str, int idx, int leftCount, int rightCount, int leftError, int rightError, StringBuilder expression){
        if(idx == str.length()){
            if(leftError == 0 && rightError == 0){
                result.add(expression.toString());
            }
            return;
        }
        //假设当前子串需要修正：当前字符为'('或者')'且对应失配数大于0时，不加入当前字符（相当于在结果中删去），向前递归。
        char c = str.charAt(idx);
        int length = expression.length();
        if((c == '(' && leftError > 0) || (c == ')' && rightError > 0)){
            Solution(str, idx + 1, leftCount, rightCount, leftError - (c == '(' ? 1 : 0), rightError - (c == ')' ? 1 : 0), expression);
        }

        //假设当前子串不需要修正：将当前字符加入子串，若加入字符后子串左括号数leftCount大于右括号数rightCount则不需要修正，向前递归。
        expression.append(c);
        //当前字符不为括号时，向前递归
        if(c != '(' && c != ')'){
            Solution(str, idx + 1, leftCount, rightCount, leftError, rightError, expression);
        }
        //当前字符为'('时，当前左括号数量leftCount+1，向前递归
        else if(c == '('){
            Solution(str, idx + 1, leftCount + 1, rightCount, leftError, rightError, expression);
        }
        //当前字符为')'时，当前右括号数量rightCount+1，向前递归
        else if(rightCount < leftCount){
            Solution(str, idx + 1, leftCount, rightCount + 1, leftError, rightError, expression);
        }
        expression.deleteCharAt(length);
    }
}
