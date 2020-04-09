package Problems;

import java.util.*;

/**
 *
 * Given a non-empty list of words, return the k most frequent elements.
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency,
 * then the word with the lower alphabetical order comes first.
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 *
 */

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for(String s : words){
            map.putIfAbsent(s, 0);
            map.put(s, map.get(s) + 1);
        }
        PriorityQueue<Word> queue = new PriorityQueue<>((a, b) -> {
            return a.times == b.times ? a.str.compareTo(b.str) : b.times - a.times;
        });
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            queue.add(new Word(entry.getKey(), entry.getValue()));
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(queue.poll().str);
        }
        return result;
    }

    private class Word{
        String str;
        int times;
        Word(String s, int t){
            str = s;
            times = t;
        }
    }
}
