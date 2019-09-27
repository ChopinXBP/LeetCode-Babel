import java.util.HashSet;

/**
 *
 * Given a string, find the length of the longest substring without repeating characters.
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 */

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int begin = 0;
        int end = 0;
        int maxlen = 0;
        int length = s.length();
        HashSet<Character> hashset = new HashSet<>();
        while(end < length){
            char c = s.charAt(end);
            if(!hashset.contains(c)){
                hashset.add(c);
            }
            else {
                while(s.charAt(begin) != c){
                    hashset.remove(s.charAt(begin));
                    begin++;
                }
                begin++;
            }
            maxlen = end - begin + 1 > maxlen ? end - begin + 1 : maxlen;
            end++;
        }
        return maxlen;
    }

    //改进版本：当end对应元素不存在HashSet中时，end前移；当end对应元素存在HashSet中时，将元素从set中删除并将begin前移，直到删去end为止
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        HashSet<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

}
