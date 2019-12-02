package Problems;

import java.util.*;

/**
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 *
 */

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int len = beginWord.length();

        //将字符替换模板“a*b”作为key，所有符合模板的字符串作为value创建哈希表，以此为边建立无向图
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(String str : wordList){
            for(int i = 0; i < len; i++){
                String pattern = str.substring(0, i) + "*" + str.substring(i + 1, len);
                map.putIfAbsent(pattern, new ArrayList<>());
                map.get(pattern).add(str);
            }
        }

        //对无向图进行BFS，利用HashSet保存已经遍历过的结点
        HashSet<String> set = new HashSet<>();
        LinkedList<String> list = new LinkedList<>();
        list.add(beginWord);
        int result = 1;
        while(!list.isEmpty()){
            int size = list.size();
            for(int i = 0; i < size; i++){
                String str = list.pollFirst();
                if(str.equals(endWord)){
                    return result;
                }
                for(int j = 0; j < len; j++){
                    String pattern = str.substring(0, j) + "*" + str.substring(j + 1, len);
                    ArrayList<String> nextList = map.getOrDefault(pattern, new ArrayList<>());
                    for(String next : nextList){
                        if(!set.contains(next)){
                            list.add(next);
                            set.add(next);
                        }
                    }
                }
            }
            result++;
        }
        return 0;
    }
}
