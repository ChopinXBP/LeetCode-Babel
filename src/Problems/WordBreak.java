package Problems;

import java.util.List;

/**
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 */

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        //dp[i]表示字典内的字符可以组成0至i-1的s的子串
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        //0-1 背包和完全背包在实现上的不同之处是，0-1 背包对物品的迭代是在最外层，而完全背包对物品的迭代是在最里层。
        for(int i = 1; i < s.length() + 1; i++){
            for(String str : wordDict){
                int len = str.length();
                if(i >= len && dp[i - len] && str.equals(s.substring(i - len, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
