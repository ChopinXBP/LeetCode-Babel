package Problems;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes
 * that can be built with those letters.
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * Note: Assume the length of given string will not exceed 1,010.
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * 注意: 假设字符串的长度不会超过 1010。
 *
 */

public class LongestPalindrome {
    public int longestPalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.putIfAbsent(c, 0);
            map.put(c, map.get(c) + 1);
        }
        int odd = 0;
        int result = 0;
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            int cur = entry.getValue();
            if((cur & 0x1) == 1){
                odd = 1;
                result += cur - 1;
                continue;
            }
            result += cur;
        }
        return result + odd;
    }
}
