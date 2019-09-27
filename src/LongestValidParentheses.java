import java.util.Stack;

/**
 *
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 */

public class LongestValidParentheses {
    //动态规划
    public int longestValidParentheses(String s) {
        int result = 0;
        //dp[i]代表以第i个括号结尾的最长有效字符串长度。第一个子串必无效，dp[1] = 0。
        int dp[] = new int[s.length() + 1];
        for(int i = 2; i <= s.length(); i++){
            //如果第i个括号为'('，则字符串必无效，dp[i]=0；只对第i个括号为')'的情况讨论
            if(s.charAt(i - 1) == ')'){
                //如果上一个括号为'('，则当前字符串形如“……()”，状态转移方程为dp[i] = dp[i - 2] + 2;
                if(s.charAt(i - 2) == '('){
                    dp[i] = dp[i - 2] + 2;
                }
                //如果上一个括号为')'，则当前字符串形如“……))”。
                //只有“s1(s2)”的形式能够满足,其中，s2必须能够满足，长度为dp[i-1]；左括号位置相应为i-dp[i-1]-2（序号-1，dp数组-1），s1的有效长度为dp[i-dp[i-1]-3+1]
                //状态转移方程为dp[i] = dp[i - 1] + (dp[i - dp[i - 1] - 2]) + 2;
                else if(i - dp[i - 1] > 1 && s.charAt(i - dp[i - 1] - 2) == '('){
                    dp[i] = dp[i - 1] + (dp[i - dp[i - 1] - 2]) + 2;
                }
                result = dp[i] > result ? dp[i] : result;
            }
        }
        return result;
    }

    //栈
    public int longestValidParentheses2(String s){
        //顺序遍历将所有遇到的'('入栈；当遇到')'时，分三种情况讨论。
        int result = 0;
        int validBeginIdx = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);
                continue;
            }
            //栈空：')'起始，子串无效，更新起始位置为i+1
            else if(stack.isEmpty()){
                validBeginIdx = i + 1;
            }
            //栈中仅1个元素：子串为"(s)"形式位于最外层，s为经过栈验证的合法子串，由起始位置validBeginIdx至当前位置i构成合法子串
            else if(stack.size() == 1){
                stack.pop();
                result = Math.max(result, i - validBeginIdx + 1);
            }
            //栈中n>1个元素：子串为"…(s()"或者"…((s)"，s为经过栈验证的合法子串，由栈顶第二个元素+1（stack.pop().peek() + 1）至当前位置i构成合法子串
            else{
                stack.pop();
                result = Math.max(result, i - (stack.peek() + 1) + 1);
            }
        }
        return result;
    }

    //动态规划：计数器法（最优）
    //分别从字符串的首尾方向进行两次扫描，使用两个计数器累加所有遇到的左右括号，当左右括号相等时，进行长度计算；当左右括号出现非法时（后括号多于前括号），计数器重置
    public int longestValidParentheses3(String s){
        int result = 0;
        int left = 0;
        int right = 0;
        //从左向右扫描
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                left++;
            }else{
                right++;
            }
            if(left == right){
                result = Math.max(result, 2 * left);
            }else if(right > left) {
                left = 0;
                right = 0;
            }
        }
        //从右向左扫描
        left = 0;
        right = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            if(s.charAt(i) == '('){
                left++;
            }else{
                right++;
            }
            if(left == right){
                result = Math.max(result, 2 * left);
            }else if(left > right) {
                left = 0;
                right = 0;
            }
        }
        return result;
    }
}
