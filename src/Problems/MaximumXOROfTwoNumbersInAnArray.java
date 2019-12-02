package Problems;

import java.util.HashSet;

/**
 *
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 * 给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。
 * 找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,j < n 。
 *
 */

public class MaximumXOROfTwoNumbersInAnArray {
    //异或性质+贪心算法
    //异或的性质：如果 a ^ b = c 成立，那么a ^ c = b 与 b ^ c = a 均成立。
    public int findMaximumXOR(int[] nums) {
        int result = 0;
        int mask = 0;
        //从最高位开始按位确定result的可能值
        for(int i = 31; i >= 0; i--){
            //mask从最高位至第i位为全1，用于截取前缀，将数组中所有数的前缀放入哈希表中
            mask |= (1 << i);
            HashSet<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }
            //在result高位确定的基础上假设第i位为1
            int temp = result | (1 << i);
            for(Integer prefix : set){
                //若哈希表中存在prifix1使得prefix2 = prefix1 ^ value，说明存在value = prefix1 ^ prefix2
                if(set.contains(prefix ^ temp)){
                    result = temp;
                    break;
                }
            }
        }
        return result;
    }

    //前缀树
    public int findMaximumXOR2(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        Trie trie = new Trie();
        return trie.getMaxXORValue(nums);
    }

    private class TrieNode{
        TrieNode[] children = new TrieNode[2];
    }

    private class Trie{
        TrieNode root;
        Trie(){
            root = new TrieNode();
        }

        public void insert(int num){
            TrieNode curNode = root;
            for(int i = 31; i >= 0; i--){
                int bit = (num >>> i) & 1;
                if(curNode.children[bit] == null){
                    curNode.children[bit] = new TrieNode();
                }
                curNode = curNode.children[bit];
            }
        }

        public int getMaxXORValue(int[] nums){
            //根据数组元素构造一棵二叉前缀树，结点值存在代表当前位上存在该数值
            for(int num : nums){
                insert(num);
            }
            int max = Integer.MIN_VALUE;
            //利用trie对每一个数num找最大异或值curMax
            for(int num : nums){
                TrieNode curNode = root;
                int curMax = 0;
                //bit代表树上该位的值（0/1），trie遍历倾向于向相反值（bit^1）走，若（bit^1）存在则curMax该位更新为1；反之向1走，curMax该位更新为0
                for(int i = 31; i >= 0; i--){
                    int bit = (num >>> i) & 1;
                    if(curNode.children[bit ^ 1] != null){
                        curMax |= (1 << i);
                        curNode = curNode.children[bit ^ 1];
                    }else {
                        curNode = curNode.children[bit];
                    }
                }
                max = curMax > max ? curMax : max;
            }
            return max;
        }
    }
}
