/**
 *
 * Given a string, your task is to count how many palindromic substrings in this string.
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 */

public class PalindromicSubstrings {
    //动态规划
    public int countSubstrings(String s) {
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for(int i = 1; i < s.length(); i++){
            dp[i] = dp[i - 1];
            for(int j = 0; j <= i; j++){
                dp[i] += isPalidromicSubstring(s, j, i) ? 1 : 0;
            }
        }
        return dp[s.length() - 1];
    }

    public boolean isPalidromicSubstring(String s, int beginIdx, int endIdx){
        while(beginIdx < endIdx){
            if(s.charAt(beginIdx++) != s.charAt(endIdx--)){
                return false;
            }
        }
        return true;
    }

    //中心拓展法
    public int countSubstrings2(String s) {
        int result = 0;
        for(int i = 0; i < s.length(); i++){
            int begin = i;
            int end = i;
            while(begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)){
                result++;
                begin--;
                end++;
            }
            begin = i - 1;
            end = i;
            while(begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)){
                result++;
                begin--;
                end++;
            }
        }
        return result;
    }

}
