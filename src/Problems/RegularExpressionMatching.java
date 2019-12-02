package Problems;

/**
 *
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 *
 */

public class RegularExpressionMatching {

    //递归匹配
    public boolean isMatch(String s, String p) {
        return Solution(s, p, 0, 0);
    }

    public boolean Solution(String s, String p, int sidx, int pidx){
        //若sidx匹配完成，可能有两种情况：p也匹配完成（true）、p是'a*b*c*……'类型，继续检查。
        if(sidx >= s.length()){
            if(pidx < p.length() - 1 && p.charAt(pidx + 1) == '*'){
                return Solution(s, p, sidx, pidx + 2);
            }
            else{
                return pidx == p.length();
            }
        }
        //若p先于s匹配完成，返回false
        if(pidx >= p.length()){
            return false;
        }

        boolean firstmatch = p.charAt(pidx) == s.charAt(sidx) || p.charAt(pidx) == '.';
        //若元素的下一位是'*',可能有两种情况：元素出现0次，或元素匹配并出现多次（包括1次），递归继续检查
        if(pidx < p.length() - 1 && p.charAt(pidx + 1) == '*'){
            return Solution(s, p, sidx, pidx + 2) || (firstmatch && Solution(s, p, sidx + 1, pidx));
        }
        //若元素匹配则继续检查，若不匹配则返回false
        else{
            return firstmatch && Solution(s, p, sidx + 1, pidx + 1);
        }
    }

    //递归精简版：substring和逻辑升级
    public boolean isMatch1(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean first_match = (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        if (p.length() >= 2 && p.charAt(1) == '*'){
            return (isMatch(s, p.substring(2)) || (first_match && isMatch(s.substring(1), p)));
        } else {
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }

    //动态规划（自头向尾）
    public boolean isMatch2(String s, String p){
        //dp[sidx][pidx]表示在长为sidx的s的子串与长为pidx的p的子串的匹配情况
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        //当s为空串的时候，p只有一种情况能够成立，即“a*b*c*……”
        //奇数字符全为false，偶数每隔两个字符检查是否是'*'，若是则其是否成立取决于上一个偶数项的情况
        for(int pidx = 2; pidx <= p.length(); pidx += 2){
            if(p.charAt(pidx - 1) == '*')
                dp[0][pidx] = dp[0][pidx - 2];
        }

        //dp[sidx][pidx]对应s和p的下标为sidx-1和pidx-1
        for(int sidx = 1; sidx <= s.length(); sidx++){
            for(int pidx = 1; pidx <= p.length(); pidx++){
                //当前元素匹配时，dp[sidx][pidx]取决于dp[sidx - 1][pidx - 1]
                if(s.charAt(sidx - 1) == p.charAt(pidx - 1) || p.charAt(pidx - 1) == '.'){
                    dp[sidx][pidx] = dp[sidx - 1][pidx - 1];
                }
                //p当前元素为'*'时
                else if(p.charAt(pidx - 1) == '*'){
                    //如果'*'的元素不匹配，则只可能是元素出现0次的情况，dp[sidx][pidx]取决于dp[sidx][pidx - 2]
                    if(p.charAt(pidx - 2) != s.charAt(sidx - 1) && p.charAt(pidx - 2) != '.'){
                        dp[sidx][pidx] = dp[sidx][pidx - 2];
                    }
                    //如果'*'的元素匹配，可能有两种情况：元素出现0次（取决于dp[sidx][pidx - 2]）、出现多次（dp[sidx - 1][pidx]，包含出现1次（取决于dp[sidx][pidx - 1]））
                    else{
                        dp[sidx][pidx] = (dp[sidx][pidx - 2] || dp[sidx - 1][pidx]);
                        //dp[sidx][pidx] = (dp[sidx][pidx - 2] || dp[sidx][pidx - 1] || dp[sidx - 1][pidx]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    //动态规划（自尾向头）
    public boolean isMatch3(String s, String p) {
        //dp[sidx][pidx]表示从尾向头长指针匹配至sidx与pidx位置的情况
        //dp[s.length()][p.length()]起始位true，当s和p均空时，可以匹配
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;

        //自尾向头更新dp
        for(int sidx = s.length(); sidx >= 0; sidx--){
            for(int pidx = p.length() - 1; pidx >= 0; pidx--){
                //记录sidx与pidx元素是否匹配。当s在最后一行时，firstmatch为false。
                boolean firstmatch = sidx < s.length() && (p.charAt(pidx) == s.charAt(sidx) || p.charAt(pidx) == '.');
                //若pidx下一个元素是'*'，可能有两种情况：元素出现0次（dp[sidx][pidx]取决于dp[sidx][pidx + 2]），元素匹配且出现多次（dp[sidx][pidx]取决于dp[sidx + 1][pidx]，包含出现一次）
                if(pidx + 1 < p.length() && p.charAt(pidx + 1) == '*'){
                    dp[sidx][pidx] = dp[sidx][pidx + 2] || (firstmatch && dp[sidx + 1][pidx]);
                }
                //若pidx下一个元素不是'*'，且元素匹配，则dp[sidx][pidx]取决于上一指针匹配情况dp[sidx + 1][pidx + 1]
                else{
                    dp[sidx][pidx] = firstmatch && dp[sidx + 1][pidx + 1];
                }
            }
        }
        return dp[0][0];
    }


}
