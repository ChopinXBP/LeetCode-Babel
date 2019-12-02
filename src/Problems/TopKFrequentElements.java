package Problems;

import java.util.*;

/**
 *
 * Given a non-empty array of integers, return the k most frequent elements.
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 */

public class TopKFrequentElements {
    class Point{
        int key;
        int value;
        Point(int k, int v){
            key = k;
            value = v;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.putIfAbsent(num, 0);
            map.replace(num, map.get(num) + 1);
        }
        PriorityQueue<Point> minheap = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.value - o2.value;
            }
        });
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(minheap.size() < k){
                minheap.add(new Point(entry.getKey(), entry.getValue()));
            }else if(entry.getValue() > minheap.peek().value){
                minheap.add(new Point(entry.getKey(), entry.getValue()));
                minheap.poll();
            }
        }
        LinkedList<Integer> result = new LinkedList<>();
        while(!minheap.isEmpty()){
            result.addFirst(minheap.poll().key);
        }
        return result;
    }
}
