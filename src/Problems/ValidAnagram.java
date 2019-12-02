package Problems;

import java.util.HashMap;

/**
 *
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 */

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }
        int[] words = new int[26];
        for (int i = 0; i < s.length(); i++){
            words[s.charAt(i) - 'a']++;
            words[t.charAt(i) - 'a']--;
        }
        for (int i : words){
            if (i != 0){
                return false;
            }
        }
        return true;
    }
}
