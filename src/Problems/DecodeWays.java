package Problems;

/**
 *
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 */

public class DecodeWays {
    //动态规划
    public int numDecodings(String s) {

        //dp[i]用于记录字符串至第i-1位前的解码方法的总数
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;

        for(int i = 1; i < s.length() + 1; i++){
            //当前数字不为0时，dp[i] += dp[i - 1]表示当前组合数量包括前一位数字前的组合总数
            char c = s.charAt(i - 1);
            if(c != '0'){
                dp[i] += dp[i - 1];
            }
            //当前数字与前一位组合的数字处于10-26时，dp[i] += dp[i - 2]表示当前组合数量包括前两位数字前的组合总数
            if(i > 1){
                int num = (s.charAt(i - 2) - '0') * 10 + (c - '0');
                //提前剪枝：连续两个0没有对应解码方式
                if(num == 0){
                    return 0;
                }
                if(num > 9 && num < 27){
                    dp[i] += dp[i - 2];
                }
            }
        }

        return s.length() == 0 ? 0 : dp[s.length()];
    }
}
