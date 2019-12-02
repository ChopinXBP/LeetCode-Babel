package Problems;

import javafx.scene.layout.Priority;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * Given a string, sort it in decreasing order based on the frequency of characters.
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 */

public class SortCharactersByFrequency {
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Word> minheap = new PriorityQueue<>(new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o2.f - o1.f;
            }
        });
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            minheap.add(new Word(entry.getKey(), entry.getValue()));
        }
        char[] result = new char[s.length()];
        int i = 0;
        while(minheap.size() != 0){
            Word word = minheap.poll();
            for(int j = 0; j < word.f; j++){
                result[i++] = word.c;
            }
        }
        return new String(result);
    }

    class Word{
        char c;
        int f;
        Word(char c, int f){
            this.c = c;
            this.f = f;
        }
    }
}
