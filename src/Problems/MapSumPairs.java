package Problems;

import java.util.HashMap;

/**
 *
 * Implement a MapSum class with insert, and sum methods.
 * For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value.
 * If the key already existed, then the original key-value pair will be overridden to the new one.
 * For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value
 * whose key starts with the prefix.
 *
 */

public class MapSumPairs {
    //前缀树
    class TrieNode{
        HashMap<Character, TrieNode> children = new HashMap<>();
        int value;
    }

    class MapSum {

        private HashMap<String, Integer> map;
        private TrieNode root;

        //前缀树每个结点存放以其为前缀的键值总和，哈希表每个结点存放插入的元素及其最新插入值
        /** Initialize your data structure here. */
        public MapSum() {
            map = new HashMap<>();
            root = new TrieNode();
        }

        //插入可能有两种情况：
        //1.key首次插入，map中不存在，前缀路径的所有结点value更新为val值
        //2.key曾插入num值，并保存在map中，val将替换num（差值delta=val-num），前缀路径所有结点value对delta更新（value+=delta）
        //最后将新的key和val保存在map中
        public void insert(String key, int val) {
            int delta = val - map.getOrDefault(key, 0);
            TrieNode node = root;
            node.value += delta;
            for(char c : key.toCharArray()){
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
                node.value += delta;
            }
            map.put(key, val);
        }

        //深度遍历prefix的前缀路径，如果出现null值则返回0，遍历结束返回尾结点的value值
        public int sum(String prefix) {
            TrieNode node = root;
            for(char c : prefix.toCharArray()){
                node = node.children.get(c);
                if(node == null){
                    return 0;
                }
            }
            return node.value;
        }
    }

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
}
