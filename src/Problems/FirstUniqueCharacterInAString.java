package Problems;

/**
 *
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 */

public class FirstUniqueCharacterInAString {
    public int firstUniqChar(String s) {
        int[] map = new int[26];
        for(int i = 0; i < s.length(); i++){
            map[s.charAt(i) - 'a']++;
        }
        for(int i = 0; i < s.length(); i++){
            if(map[s.charAt(i)] == 1){
                return i;
            }
        }
        return -1;
    }
}
