/**
 *
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
 * Note: Each term of the sequence of integers will be represented as a string.
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 * 注意：整数顺序将表示为一个字符串。
 *
 */

public class CountAndSay {
    public String countAndSay(int n) {
        if(n <= 0){
            return "";
        }
        StringBuilder result = new StringBuilder("1");
        while(n-- > 1){
            int idx = 0;
            int len = result.length();
            StringBuilder newstr = new StringBuilder();
            while(idx < len){
                int count = 1;
                char c = result.charAt(idx);
                while(idx + 1 < len && result.charAt(idx + 1) == c){
                    idx++;
                    count++;
                }
                newstr.append("" + count + c);
                idx++;
            }
            result = newstr;
        }
        return result.toString();
    }
}
