import java.util.*;

/**
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * 说明：
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 */

public class WordBreakII {

    //动态规划（超时）
    //超时用例：
    //"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    //["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>();
        for(String str : wordDict){
            dict.add(str);
        }

        //ismatch[i]表示字典内的字符能否组成0至i-1的s的子串
        boolean[] ismatch = new boolean[s.length() + 1];
        ismatch[0] = true;
        for(int i = 1; i < s.length() + 1; i++){
            for(String str : wordDict){
                int len = str.length();
                if(i >= len && ismatch[i - len] && str.equals(s.substring(i - len, i))){
                    ismatch[i] = true;
                    break;
                }
            }
        }

        //dp[i]表示字典内的字符组成0至i-1的s的字符串
        ArrayList<String>[] dp = new ArrayList[s.length() + 1];
        dp[0] = new ArrayList<>();
        dp[0].add("");

        for(int i = 1; i <= s.length(); i++){
            //跳过不能构成字符串的拆分项
            dp[i] = new ArrayList<>();
            if(!ismatch[i]){
                continue;
            }
            for(int j = 0; j < i; j++){
                //0-j有符合的字符串，且j-i构成的单词str在字典中，那么可以对dp[j]中的所有字符串加上str
                if(!ismatch[j]) {
                    continue;
                }
                if(dict.contains(s.substring(j, i))){
                    String str = s.substring(j, i);
                    for(String string : dp[j]){
                        dp[i].add(string + (string.equals("") ? "" : " ") + str);
                    }
                }
            }
        }

        return dp[s.length()];
    }

    //Memorized DFS
    public List<String> wordBreak2(String s, List<String> wordDict){
        int len = s.length();
        //将所有word存入哈希表
        int maxLen = 0;
        HashSet<String> dict = new HashSet<>();
        for(String str : wordDict){
            dict.add(str);
            maxLen = str.length() > maxLen ? str.length() : maxLen;
        }
        //动态规划，预先判定字典内的字符能否组成0至i-1的s的子串
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for(int i = 1; i <= len; i++){
            for(int j = Math.max(0, i - maxLen); j < i; j++){
                if(dp[j] && dict.contains(s.substring(j , i))){
                    dp[i] = true;
                    break;
                }
            }
        }

        return DFS(s, dict, new HashMap<>(), dp);
    }

    public ArrayList<String> DFS(String s, HashSet<String> dict, HashMap<String, ArrayList<String>> map, boolean[] dp){
        //如果字符串s能够由多个word拆分而成，且已经计算并存储进map，直接返回。
        if(map.containsKey(s)){
            return map.get(s);
        }
        ArrayList<String> result = new ArrayList<>();
        if(s.length() == 0){
            return result;
        }
        //如果字符串s能够直接构成字典中的单词，则直接将其加入结果列表
        if(dict.contains(s)){
            result.add(s);
        }

        //i从后往前截断字符串，将其分为pre（0-i）与cur（i-end）
        int end = s.length();
        for(int i = end - 1; i >= 0; i--){
            String cur = s.substring(i, end);
            //如果cur在dict中，且pre能够构成拆分子串，则递归对pre进行计算，将计算结果拼接成字符串，存储在map中记忆，并返回。
            if(dict.contains(cur) && dp[i]){
                ArrayList<String> list = DFS(s.substring(0, i), dict, map, dp);
                for(String str : list){
                    result.add(str + " " + cur);
                }
            }
        }
        map.put(s, result);
        return result;
    }

    //Set版
    public class Solution {

        public List<String> wordBreak(String s, Set<String> wordDict) {
            return word_Break(s, wordDict, 0);
        }
        HashMap<Integer, List<String>> map = new HashMap<>();

        public List<String> word_Break(String s, Set<String> wordDict, int start) {
            if (map.containsKey(start)) {
                return map.get(start);
            }
            LinkedList<String> res = new LinkedList<>();
            if (start == s.length()) {
                res.add("");
            }
            for (int end = start + 1; end <= s.length(); end++) {
                if (wordDict.contains(s.substring(start, end))) {
                    List<String> list = word_Break(s, wordDict, end);
                    for (String l : list) {
                        res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                    }
                }
            }
            map.put(start, res);
            return res;
        }
    }
}
