package Problems;

/**
 *
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words,
 * one of the first string's permutations is the substring of the second string.
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 */

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if(s2.length() < s1.length()){
            return false;
        }
        if(s1.length() == 0){
            return true;
        }
        int[] pattern = new int[26];
        for(char c : s1.toCharArray()){
            pattern[c - 'a']++;
        }
        int[] window = new int[26];
        for(int i = 0; i < s1.length() - 1; i++) {
            window[s2.charAt(i) - 'a']++;
        }
        int begin = 0;
        for(int end = s1.length() - 1; end < s2.length(); end++){
            window[s2.charAt(end) - 'a']++;
            if(isMatched(pattern, window)){
                return true;
            }
            window[s2.charAt(begin++) - 'a']--;
        }
        return false;
    }

    public boolean isMatched(int[] pattern, int[] window){
        for(int i = 0; i < 26; i++){
            if(pattern[i] != window[i]){
                return false;
            }
        }
        return true;
    }
}
