package Problems;

/**
 *
 * Design a HashMap without using any built-in hash table libraries.
 * 不使用任何内建的哈希表库设计一个哈希映射
 *
 */

public class DesignHashMap {

    class MyHashMap {

        private class Node{
            int key;
            int value;
            Node next;
            public Node(int k, int v, Node n){
                key = k;
                value = v;
                next = n;
            }
        }

        private final double LOAD_FACTOR = 0.75;
        private Node[] nodes;
        private int size;

        /** Initialize your data structure here. */
        public MyHashMap() {
            nodes = new Node[16];
            size = 0;
        }

        //计算哈希值（当length为2的幂次时，key & (length - 1) == key % length）
        private int hash(int key){
            return key & (nodes.length - 1);
        }

        //每次再散列成原数组的两倍
        private void rehash(){
            Node[] temp = nodes;
            nodes = new Node[temp.length << 1];
            size = 0;
            for(Node head : temp){
                for(Node p = head; p != null; p = p.next){
                    put(p.key, p.value);
                }
            }
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int hashkey = hash(key);
            //先遍历链表里是否已经存在相同key值，若存在则覆盖value
            for(Node p = nodes[hashkey]; p != null; p = p.next){
                if(p.key == key){
                    p.value = value;
                    return;
                }
            }
            //若链表中不存在则将结点加在开头
            nodes[hashkey] = new Node(key, value, nodes[hashkey]);
            size++;
            //若当前size比例超过负债系数，则进行再散列
            if((double)size / nodes.length > LOAD_FACTOR){
                rehash();
            }
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int hashkey = hash(key);
            for(Node p = nodes[hashkey]; p != null; p = p.next){
                if(p.key == key){
                    return p.value;
                }
            }
            return -1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int hashkey = hash(key);
            Node newhead = new Node(-1, -1, nodes[hashkey]);
            for(Node p = newhead; p.next != null; p = p.next){
                if(p.next.key == key){
                    p.next = p.next.next;
                    size--;
                    break;
                }
            }
            nodes[hashkey] = newhead.next;
        }
    }

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
}
