package Problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 */

public class GenerateParentheses {
    public static void main(String[] args) {
        List<String> answer = generateParenthesis(4);
        for (String s : answer) {
            System.out.println(s);
        }
    }

    //递归（由外向内）
    private static HashSet<String> result;

    public static List<String> generateParenthesis(int n) {
        ArrayList<String> answer = new ArrayList<>();
        if (n <= 0) {
            return answer;
        }
        result = new HashSet<>();
        Solution(n, new StringBuilder("()"));
        answer.addAll(result);
        return answer;
    }

    public static void Solution(int n, StringBuilder str) {
        if (n == 1) {
            result.add(str.toString());
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                Solution(n - 1, new StringBuilder(str).insert(i + 1, "()"));
            }
        }
        Solution(n - 1, new StringBuilder(str).insert(0, "()"));
        Solution(n - 1, new StringBuilder(str).append("()"));
    }

    //回溯法:最优（由前向后）
    public List<String> generateParenthesis1(int n) {
        ArrayList<String> answer = new ArrayList();
        Solution(answer, "", 0, 0, n);
        return answer;
    }

    public void Solution(ArrayList<String> answer, String cur, int open, int close, int n) {
        if (cur.length() == (n << 1)) {
            answer.add(cur);
            return;
        }
        //每次在尾部添加括号，保证任意情况下左括号数目不小于右括号数目即可
        //左括号未使用完，可以继续添加左括号
        if (open < n)
            Solution(answer, cur + "(", open + 1, close, n);
        //在右括号使用数量小于左括号的情况下，可以添加右括号
        if (open > close)
            Solution(answer, cur + ")", open, close + 1, n);
    }

    //不相交子集（由内向外）
    public List<String> generateParenthesis2(int n) {
        ArrayList<String> answer = new ArrayList();
        if (n == 0) {
            answer.add("");
        }
        //对于一个有效的括号组合，其一定可以表示为"(" + left + ")" + right的形式，其中left长度为num，right长度为n-num-1，left和right一定也是有效的括号组合。
        for (int num = 0; num < n; ++num) {
            for (String left : generateParenthesis2(num)) {
                for (String right : generateParenthesis2(n - num - 1)) {
                    answer.add("(" + left + ")" + right);
                }
            }
        }
        return answer;
    }
}
