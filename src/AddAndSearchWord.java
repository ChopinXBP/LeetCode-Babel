/**
 *
 * Design a data structure that supports the following two operations:
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 * 设计一个支持以下两种操作的数据结构：
 * void addWord(word)
 * bool search(word)
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 *
 */

public class AddAndSearchWord {
    class WordDictionary {

        private Trie trie;

        /** Initialize your data structure here. */
        public WordDictionary() {
            trie = new Trie();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            trie.insert(word);
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return trie.prefix(word);
        }

        private class Trie{
            TrieNode root;
            public Trie(){
                root = new TrieNode();
            }

            public void insert(String word){
                TrieNode node = root;
                for(char c : word.toCharArray()){
                    int idx = c - 'a';
                    if(node.children[idx] == null){
                        node.children[idx] = new TrieNode();
                    }
                    node = node.children[idx];
                }
                node.end = true;
            }

            public boolean prefix(String word){
                return prefix(word, 0, root);
            }

            public boolean prefix(String word, int curIdx, TrieNode curNode){
                TrieNode node = curNode;
                char[] chars = word.toCharArray();
                for(int i = curIdx; i < chars.length; i++){
                    if(chars[i] == '.'){
                        for(TrieNode next : node.children){
                            if(next != null && prefix(word, i + 1, next)){
                                return true;
                            }
                        }
                        return false;
                    }
                    int idx = chars[i] - 'a';
                    if(node.children[idx] == null){
                        return false;
                    }
                    node = node.children[idx];
                }
                return node.end;
            }
        }

        private class TrieNode{
            TrieNode[] children = new TrieNode[26];
            boolean end = false;
        }
    }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
}
