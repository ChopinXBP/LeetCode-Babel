import java.util.List;

/**
 *
 * In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor.
 * For example, the root an, followed by other, which can form another word another.
 * Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it.
 * If a successor has many roots can form it, replace it with the root with the shortest length.
 * You need to output the sentence after the replacement.
 * 在英语中，我们有一个叫做 词根(root)的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。
 * 例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 * 现在，给定一个由许多词根组成的词典和一个句子。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * 你需要输出替换之后的句子。
 *
 */

public class ReplaceWords {
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
        public int prefix(String word){
            TrieNode node = root;
            char[] chars = word.toCharArray();
            for(int i = 0; i < chars.length; i++){
                int idx = chars[i] - 'a';
                if(node.children[idx] == null){
                    return node.end ? i : -1;
                }
                if(node.end){
                    return i;
                }
                node = node.children[idx];
            }
            return -1;
        }
    }

    private class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean end = false;
    }

    public String replaceWords(List<String> dict, String sentence) {
        Trie trie = new Trie();
        for(String word : dict){
            trie.insert(word);
        }
        String[] strs = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < strs.length; i++){
            int idx = trie.prefix(strs[i]);
            if(i != 0){
                result.append(" ");
            }
            result.append(idx == -1 ? strs[i] : strs[i].substring(0, idx));
        }
        return result.toString();
    }
}
