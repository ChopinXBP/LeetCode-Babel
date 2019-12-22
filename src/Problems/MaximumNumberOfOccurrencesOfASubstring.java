package Problems;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a string s, return the maximum number of ocurrences of any substring under the following rules:
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 * 给你一个字符串 s ，请你返回满足以下条件且出现次数最大的 任意 子串的出现次数：
 * 子串中不同字母的数目必须小于等于 maxLetters 。
 * 子串的长度必须大于等于 minSize 且小于等于 maxSize 。
 *
 */

public class MaximumNumberOfOccurrencesOfASubstring {
    /**
     * 原思路解法：滑动窗口
     */
    public int maxFreq0(String s, int maxLetters, int minSize, int maxSize) {
        int[] words = new int[26];
        int curNum = 0;
        for(int i = 0; i < minSize - 1; i++) {
            int idx = s.charAt(i) - 'a';
            if(words[idx]++ == 0){
                curNum++;
            }
        }

        HashMap<String, Integer> map = new HashMap<>();
        for(int begin = 0; begin <= s.length() - minSize; begin++) {
            int end = begin + minSize - 1;
            if(curNum <= maxLetters){
                int tail = Math.min(begin + maxSize - 1, s.length() - 1);
                int[] newWord = words.clone();
                int newNum = curNum;
                for(int i = end; i <= tail; i++) {
                    int idx = s.charAt(i) - 'a';
                    if(newWord[idx]++ == 0){
                        newNum++;
                    }
                    if(newNum > maxLetters){
                        break;
                    }
                    String str = s.substring(begin, i + 1);
                    map.putIfAbsent(str, 0);
                    map.put(str, map.get(str) + 1);
                }
            }
            int beginIdx = s.charAt(begin) - 'a';
            if(words[beginIdx]-- == 1){
                curNum--;
            }
            int endIdx = s.charAt(end) - 'a';
            if(words[endIdx]++ == 0){
                curNum++;
            }
        }

        int result = 0;
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            result = Math.max(result, entry.getValue());
        }
        return result;
    }

    /**
     * 改进解法：只需要创建minSize的滑动窗口即可，如果长度为maxSize的子串出现了N次，那么长度为minSize的子串也会出现N次
     */
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int[] words = new int[26];
        int curNum = 0;
        for(int i = 0; i < minSize - 1; i++) {
            if(words[s.charAt(i) - 'a']++ == 0){
                curNum++;
            }
        }

        HashMap<String, Integer> map = new HashMap<>();
        int end = minSize - 1;
        for(int begin = 0; begin <= s.length() - minSize; begin++) {
            if(words[s.charAt(end) - 'a']++ == 0){
                curNum++;
            }
            if(curNum <= maxLetters){
                String str = s.substring(begin, end + 1);
                map.putIfAbsent(str, 0);
                map.put(str, map.get(str) + 1);
            }
            end++;
            if(words[s.charAt(begin) - 'a']-- == 1){
                curNum--;
            }
        }

        int result = 0;
        for(int num : map.values()){
            result = num > result ? num : result;
        }
        return result;
    }
}
