package Problems;

/**
 *
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 */

public class BasicCalculatorII {
    //不借助栈
    public int calculate(String s) {
        int result = 0;
        int popSum = 0;
        int num = 0;
        char lastSign = '+';
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num * 10 + (c - '0');
            }
            if(i == s.length() - 1 || !Character.isDigit(c) && c != ' '){
                switch (lastSign){
                    case '+':
                        result += popSum;
                        popSum = num;
                        break;
                    case '-':
                        result += popSum;
                        popSum = -num;
                        break;
                    case '*':
                        popSum *= num;
                        break;
                    case '/':
                        popSum /= num;
                        break;
                    default:
                        break;
                }
                lastSign = c;
                num = 0;
            }
        }

        return result + popSum;
    }
}
