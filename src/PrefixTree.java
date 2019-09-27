/**
 * 
 * @author ChopinXBP
 * Implement a trie with insert, search, and startsWith methods.
 * Note: You may assume that all inputs are consist of lowercase letters a-z. All inputs are guaranteed to be non-empty strings.
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * 说明: 你可以假设所有的输入都是由小写字母 a-z 构成的。保证所有输入均为非空字符串。
 * https://leetcode.com/articles/implement-trie-prefix-tree/
 * 
 */

public class PrefixTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie trie = new Trie();

		trie.insert("apple");
		System.out.println(trie.search("apple"));   // 返回 true
		System.out.println(trie.search("app"));		// 返回 false
		System.out.println(trie.startsWith("app"));	// 返回 true
		trie.insert("app"); 
		System.out.println(trie.search("app"));		// 返回 true
 
	}
	
	
}

//前缀树类
class Trie {
	
	private TrieNode root;
	
    /** Initialize your data structure here. */
    public Trie() {
    	root = new TrieNode();
    }

    
    /** Inserts a word into the trie. */
    //插入时间复杂度o(m)，空间复杂度o(m)
    public void insert(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
        	char currentChar = word.charAt(i);
        	if(!node.containsKey(currentChar)) {
        		node.put(currentChar, new TrieNode());
        	}
        	node = node.get(currentChar);
        }
        node.setEnd();
    }

    // search a prefix or whole key in trie and
    // returns the node where search ends
    //若前缀码存在，则返回前缀码word的最尾结点，否则返回空
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
        	char curLetter = word.charAt(i);
        	if(node.containsKey(curLetter)) {
        		node = node.get(curLetter);
        	}else {
        		return null;
        	}
        }
        return node;
    }
    
    /** Returns if the word is in the trie. */
    //当且仅当前缀存在且当前结点为叶子结点时返回真
    //查找时间复杂度o(m)，空间复杂度o(1)
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    //前缀存在时返回真
    //查找时间复杂度o(m)，空间复杂度o(1)
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}

//前缀树结点类
class TrieNode {

    // R links to node children
	//TrieNode数组在构造函数中初始化长为R，位置代表当前字母，值存放指向子结点的指针
    private TrieNode[] links;

    //代表可能的字符值数量，决定links长度，一般为26（26个字母）
    private final int R = 26;

    //当isEnd为true时，当前结点为该键值的尾结点（尾结点links数组不存值）
    private boolean isEnd;

    public TrieNode() {
        links = new TrieNode[R];
    }

    //判断当前结点对应字符是否为ch，若link对应位置不为null则返回true
    public boolean containsKey(char ch) {
        return links[ch -'a'] != null;
    }
    
    //返回字母ch对应的子结点
    public TrieNode get(char ch) {
        return links[ch -'a'];
    }
    
    //将字母ch对应位置放入新子结点
    public void put(char ch, TrieNode node) {
        links[ch -'a'] = node;
    }
    
    //将当前结点设置为尾结点
    public void setEnd() {
        isEnd = true;
    }
    
    //判断当前结点是否尾结点
    public boolean isEnd() {
        return isEnd;
    }
}