/**
 * 
 * @author ChopinXBP
 * Implement a trie with insert, search, and startsWith methods.
 * Note: You may assume that all inputs are consist of lowercase letters a-z. All inputs are guaranteed to be non-empty strings.
 * ʵ��һ�� Trie (ǰ׺��)������ insert, search, �� startsWith ������������
 * ˵��: ����Լ������е����붼����Сд��ĸ a-z ���ɵġ���֤���������Ϊ�ǿ��ַ�����
 * https://leetcode.com/articles/implement-trie-prefix-tree/
 * 
 */

public class PrefixTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie trie = new Trie();

		trie.insert("apple");
		System.out.println(trie.search("apple"));   // ���� true
		System.out.println(trie.search("app"));		// ���� false
		System.out.println(trie.startsWith("app"));	// ���� true
		trie.insert("app"); 
		System.out.println(trie.search("app"));		// ���� true
 
	}
	
	
}

//ǰ׺����
class Trie {
	
	private TrieNode root;
	
    /** Initialize your data structure here. */
    public Trie() {
    	root = new TrieNode();
    }

    
    /** Inserts a word into the trie. */
    //����ʱ�临�Ӷ�o(m)���ռ临�Ӷ�o(m)
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
    //��ǰ׺����ڣ��򷵻�ǰ׺��word����β��㣬���򷵻ؿ�
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
    //���ҽ���ǰ׺�����ҵ�ǰ���ΪҶ�ӽ��ʱ������
    //����ʱ�临�Ӷ�o(m)���ռ临�Ӷ�o(1)
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    //ǰ׺����ʱ������
    //����ʱ�临�Ӷ�o(m)���ռ临�Ӷ�o(1)
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}

//ǰ׺�������
class TrieNode {

    // R links to node children
	//TrieNode�����ڹ��캯���г�ʼ����ΪR��λ�ô���ǰ��ĸ��ֵ���ָ���ӽ���ָ��
    private TrieNode[] links;

    //������ܵ��ַ�ֵ����������links���ȣ�һ��Ϊ26��26����ĸ��
    private final int R = 26;

    //��isEndΪtrueʱ����ǰ���Ϊ�ü�ֵ��β��㣨β���links���鲻��ֵ��
    private boolean isEnd;

    public TrieNode() {
        links = new TrieNode[R];
    }

    //�жϵ�ǰ����Ӧ�ַ��Ƿ�Ϊch����link��Ӧλ�ò�Ϊnull�򷵻�true
    public boolean containsKey(char ch) {
        return links[ch -'a'] != null;
    }
    
    //������ĸch��Ӧ���ӽ��
    public TrieNode get(char ch) {
        return links[ch -'a'];
    }
    
    //����ĸch��Ӧλ�÷������ӽ��
    public void put(char ch, TrieNode node) {
        links[ch -'a'] = node;
    }
    
    //����ǰ�������Ϊβ���
    public void setEnd() {
        isEnd = true;
    }
    
    //�жϵ�ǰ����Ƿ�β���
    public boolean isEnd() {
        return isEnd;
    }
}