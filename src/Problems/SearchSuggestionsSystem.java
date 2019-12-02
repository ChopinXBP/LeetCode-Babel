package Problems;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each
 * character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix
 * return the three lexicographically minimums products.
 * Return list of lists of the suggested products after each character of searchWord is typed.
 * 给你一个产品数组 products 和一个字符串 searchWord ，products  数组中每个产品都是一个字符串。
 * 请你设计一个推荐系统，在依次输入单词 searchWord 的每一个字母后，推荐 products 数组中前缀与 searchWord 相同的最多三个产品。如果前缀相同的可推荐产品超过三个，请按字典序返回最小的三个。
 * 请你以二维列表的形式，返回在输入 searchWord 每个字母后相应的推荐产品的列表。
 *
 */

public class SearchSuggestionsSystem {
    private class TrieNode{
        boolean end = false;
        String str = null;
        int count = 0;
        TrieNode[] children = new TrieNode[26];
    }

    private class Trie{
        TrieNode root = new TrieNode();
        public void insert(String[] products){
            for(String str : products){
                insertWord(str);
            }
        }
        private void insertWord(String products){
            TrieNode node = root;
            for(char c : products.toCharArray()){
                if(node.children[c - 'a'] == null){
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            if(node.end != true){
                node.end = true;
                node.str = products;
            }
            node.count++;
        }
        public List<List<String>> searchWord(String word){
            List<List<String>> result = new ArrayList<>();
            for(int i = 1; i <= word.length(); i++){
                result.add(search(word.substring(0, i)));
            }
            return result;
        }
        private List<String> search(String pattern){
            List<String> result = new ArrayList<>();
            TrieNode node = root;
            for(char c : pattern.toCharArray()){
                if(node.children[c - 'a'] == null){
                    return result;
                }
                node = node.children[c - 'a'];
            }
            Solution(node, result);
            return result;
        }
        private void Solution(TrieNode root, List<String> result){
            if(root.end){
                for(int i = 0; i < root.count; i++){
                    result.add(root.str);
                    if(result.size() == 3){
                        return;
                    }
                }
            }
            for(TrieNode node : root.children){
                if(node != null){
                    Solution(node, result);
                }
                if(result.size() == 3){
                    return;
                }
            }
        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie trie = new Trie();
        trie.insert(products);
        return trie.searchWord(searchWord);
    }
}
