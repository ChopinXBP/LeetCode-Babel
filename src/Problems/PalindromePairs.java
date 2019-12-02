package Problems;

import java.util.*;

/**
 *
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words,
 * i.e. words[i] + words[j] is a palindrome.
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 *
 */

public class PalindromePairs {

    //字典树
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        TrieNode root = new TrieNode();
        for(int i = 0; i < words.length; i++){
            addWord(root, words[i], i);
        }
        for(int i = 0; i < words.length; i++){
            search(words, i, root, result);
        }
        return result;
    }

    private class TrieNode{
        TrieNode[] children;
        int reverseWordIdx;
        List<Integer> palindPrefixWords;
        TrieNode(){
            children = new TrieNode[26];
            reverseWordIdx = -1;
            palindPrefixWords = new ArrayList<>();
        }
    }

    //构建后缀树
    private void addWord(TrieNode root, String word, int curWordIdx){
        //从前往后遍历每一个字符i进行建树
        for(int i = word.length() - 1; i >= 0; i--){
            int loc = word.charAt(i) - 'a';
            if(root.children[loc] == null){
                root.children[loc] = new TrieNode();
            }
            //palindPrefixWords存放在i处除去后缀其余部分能形成回文前缀[0,i]的单词序号
            if(isPalindrome(word, 0, i)){
                root.palindPrefixWords.add(curWordIdx);
            }
            root = root.children[loc];
        }
        //考虑前缀为空串时[0,0]也是回文串，尾结点处palindPrefixWords添加curWordIdx
        root.palindPrefixWords.add(curWordIdx);
        //尾结点的reverseWordIdx存放翻转单词的单词序号
        root.reverseWordIdx = curWordIdx;
    }

    private void search(String[] words, int curWordIdx, TrieNode node, List<List<Integer>> result){
        //从前往后遍历单词curWord的进行校验
        for(int j = 0; j < words[curWordIdx].length(); j++){
            //如果字典中恰存在一个与curWord[0,j-1]互为翻转的单词reverseWord，且剩余部分curWord[j,end]恰为回文子串
            //则一定能组合成curWord[0,j-1]+curWord[j,end]+reverseWord[0,end]的回文串
            if(node.reverseWordIdx >= 0 && isPalindrome(words[curWordIdx], j, words[curWordIdx].length() - 1)){
                result.add(Arrays.asList(curWordIdx, node.reverseWordIdx));
            }
            node = node.children[words[curWordIdx].charAt(j) - 'a'];
            //若后缀路径遇到空值则停止校验并返回
            if(node == null){
                return;
            }
        }
        //若遍历结束结点仍不空，说明curWord[0,end]恰与字典中其他单词的部分后缀[j,end]互为翻转
        //除去翻转后缀后其余部分[0,j-1]能够形成回文的单词palindPrefixWord一定能与curWord构成
        //curWord[0,end]+palindPrefixWord[0,j-1]+palindPrefixWord[j,end]的回文串（j-1=0时为空回文子串，字典中恰有两个单词互为翻转）
        for(int palindPrefixWordIdx : node.palindPrefixWords){
            //排除自身构成回文的重复情况
            if(curWordIdx != palindPrefixWordIdx){
                result.add(Arrays.asList(curWordIdx, palindPrefixWordIdx));
            }
        }
    }

    private boolean isPalindrome(String word, int begin, int end){
        while(begin < end){
            if(word.charAt(begin++) != word.charAt(end--)){
                return false;
            }
        }
        return true;
    }

    //哈希表
    public List<List<Integer>> palindromePairs2(String[] words) {

        HashMap<String, Integer> map = new HashMap<>();
        int emptyLoc = -1;
        for(int i = 0; i < words.length; i++){
            if(words[i].equals("")){
                emptyLoc = i;
                continue;
            }
            map.put(words[i], i);
        }

        HashSet<List<Integer>> result = new HashSet<>();
        for(int i = 0; i < words.length; i++){
            String str = words[i];
            if(str.equals("")){
                continue;
            }
            //如果words里有空串，则配合当前字符串可以组成回文对
            if(emptyLoc != -1 && isPalindrome(str, 0, str.length() - 1)){
                result.add(Arrays.asList(emptyLoc, i));
                result.add(Arrays.asList(i, emptyLoc));
            }
            //如果当前串的翻转串也在words中（与本身不同），则可以组成回文对
            String revStr = reverseStr(str);
            if(map.containsKey(revStr) && !str.equals(revStr)){
                result.add(Arrays.asList(map.get(revStr), i));
                result.add(Arrays.asList(i, map.get(revStr)));
            }
            //如果将字符串分割后有一半是回文串，且另一半的翻转在数组中，则可以组成回文对
            for(int j = 1; j < str.length(); j++){
                String left = str.substring(0, j);
                String right = str.substring(j);
                if(isPalindrome(left, 0, left.length() - 1)){
                    String revRight = reverseStr(right);
                    if(map.containsKey(revRight)){
                        result.add(Arrays.asList(map.get(revRight), i));
                    }
                }
                if(isPalindrome(right, 0, right.length() - 1)){
                    String revLeft = reverseStr(left);
                    if(map.containsKey(revLeft)){
                        result.add(Arrays.asList(i, map.get(revLeft)));
                    }
                }
            }
        }

        return new ArrayList<>(result);
    }

    public String reverseStr(String str){
        StringBuilder result = new StringBuilder(str);
        return result.reverse().toString();
    }

}
