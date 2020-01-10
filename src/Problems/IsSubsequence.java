package Problems;

/**
 *
 * Given a string s and a string t, check if s is subsequence of t.
 * You may assume that there is only lower case English letters in both s and t.
 * t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters
 * without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 */

public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int sIdx = 0;
        int tIdx = 0;
        while(sIdx < s.length()){
            while(tIdx < t.length() && s.charAt(sIdx) != t.charAt(tIdx)){
                tIdx++;
            }
            if(tIdx == t.length()){
                return false;
            }
            sIdx++;
            tIdx++;
        }
        return true;
    }
}
