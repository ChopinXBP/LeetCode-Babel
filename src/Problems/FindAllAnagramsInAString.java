package Problems;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * 说明：字母异位词指字母相同，但排列不同的字符串。不考虑答案输出的顺序。
 *
 */

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> result = new ArrayList<>();
        if(s.length() < p.length()){
            return result;
        }
        int[] pattern = new int[26];
        for(int i = 0; i < p.length(); i++){
            pattern[p.charAt(i) - 'a']++;
        }
        int[] string = new int[26];
        for(int i = 0; i < p.length() - 1; i++){
            string[s.charAt(i) - 'a']++;
        }

        int beginIdx = 0;
        for(int i = p.length() - 1; i < s.length(); i++){
            int idx = s.charAt(i) - 'a';
            string[idx]++;
            if(string[idx] != 0 && Solution(string, pattern)){
                result.add(beginIdx);
            }
            string[s.charAt(beginIdx++) - 'a']--;
        }

        return result;
    }

    public boolean Solution(int[] string, int[] pattern){
        for(int i = 0; i < 26; i++){
            if(string[i] != pattern[i]){
                return false;
            }
        }
        return true;
    }
}
