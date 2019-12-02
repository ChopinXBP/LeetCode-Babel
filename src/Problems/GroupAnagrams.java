package Problems;

import java.util.*;

/**
 *
 * Given an array of strings, group anagrams together.
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 */

public class GroupAnagrams {

    //将排序后的字符数组作为key
    //相同值不同哈希码的charArray变量依次使用Object.toString()之后hashCode()不同，依次使用String.valueOf()之后hashCode()相同。
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(String str : strs){
            char[] cs = str.toCharArray();
            Arrays.sort(cs);
            // 不能用String key = cs.toString();，直接在常量池中新建对象。
            // String key = String.valueOf(cs);先检查常量池中的对象，没有才新建。
            String key = String.valueOf(cs);
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList(map.values());
    }

    //统计字母出现次数作为key（改进：用质数表示每一个字母，用乘积作为字符串的key）
    //相同值不同哈希码的StringBuildrer变量依次使用String.valueOf()/Object.toString()之后hashCode()相同，依次使用toString()之后hashCode()也相同。
    public List<List<String>> groupAnagrams2(String[] strs){
        if(strs.length <= 0){
            return new ArrayList<>();
        }
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(String str : strs){
            char[] cs = str.toCharArray();
            int[] count = new int[26];
            for(char c : cs){
                ++count[c - 'a'];
            }
            StringBuilder s = new StringBuilder("");
            for(int num : count){
                s.append(num);
            }
            //使用String.valueOf()或者toString()均可。
            String key = String.valueOf(s);
            //String key = s.toString();
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList(map.values());
    }
}
