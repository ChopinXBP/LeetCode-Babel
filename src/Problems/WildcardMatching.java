package Problems;

/**
 *
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * Note:
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 *
 */

public class WildcardMatching {

    //动态规划：自头向尾
    public boolean isMatch(String s, String p) {
        //dp[sidx][pidx]代表长为sidx的s的子串与长为pidx的p的子串的匹配情况
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for(int i = 1; i <= p.length(); i++){
            if(p.charAt(i - 1) != '*'){
                break;
            }
            dp[0][i] = dp[0][i - 1];
        }

        for(int sidx = 1; sidx <= s.length(); ++sidx){
            for(int pidx = 1; pidx <= p.length(); ++pidx){
                //若当前字符匹配或者p串字符为‘？’，dp[sidx][pidx]取决于dp[sidx - 1][pidx - 1]
                if(p.charAt(pidx - 1) == s.charAt(sidx - 1) || p.charAt(pidx - 1) == '?'){
                    dp[sidx][pidx] = dp[sidx - 1][pidx - 1];
                }
                //若当前p串字符为‘*’，dp[sidx][pidx]取决于dp[sidx][pidx - 1]（出现0次）或者dp[sidx - 1][pidx]（出现多次）;
                else if(p.charAt(pidx - 1) == '*'){
                    dp[sidx][pidx] = dp[sidx][pidx - 1] || dp[sidx - 1][pidx];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    //动态规划：自尾向头
    public boolean isMatch2(String s, String p){
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        for(int i = p.length() - 1; i >= 0; --i){
            if(p.charAt(i) != '*'){
                break;
            }
            dp[s.length()][i] = dp[s.length()][i + 1];
        }

        for(int sidx = s.length() - 1; sidx >= 0; --sidx){
            for(int pidx = p.length() - 1; pidx >= 0; --pidx){
                if(p.charAt(pidx) == s.charAt(sidx) || p.charAt(pidx) == '?'){
                    dp[sidx][pidx] = dp[sidx + 1][pidx + 1];
                }
                else if(p.charAt(pidx) == '*'){
                    dp[sidx][pidx] = dp[sidx + 1][pidx] || dp[sidx][pidx + 1];
                }
            }
        }

        return dp[0][0];
    }
}
