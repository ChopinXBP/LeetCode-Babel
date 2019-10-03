import java.util.Stack;

/**
 *
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 * For example, there won't be input like 3a or 2[4].
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 */

public class DecodeString {
    //借助辅助栈遍历字符串，遇到数字时，栈中依次存入：数字的起始位置，数字大小，字符串的起始位置；遇到']'，对原字符串进行替换并重置计数器位置
    public String decodeString(String s) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                stack.push(i);
                int times = c - '0';
                while(Character.isDigit(s.charAt(++i))){
                    times = times * 10 + s.charAt(i) - '0';
                }
                stack.push(times);
                stack.push(i);
                continue;
            }else if(c == ']'){
                String str = s.substring(stack.pop() + 1, i);
                int times = stack.pop();
                StringBuilder newstr = new StringBuilder(str);
                for(int j = 1; j < times; j++){
                    newstr.append(str);
                }
                int beginIdx = stack.pop();
                s = s.substring(0, beginIdx) + newstr + s.substring(i + 1);
                //注意要重置i的位置
                i = beginIdx + newstr.length() - 1;
            }
        }
        return s;
    }
}
