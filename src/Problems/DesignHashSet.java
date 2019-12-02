package Problems;

/**
 *
 * Design a HashSet without using any built-in hash table libraries.
 * 不使用任何内建的哈希表库设计一个哈希集合
 *
 */

public class DesignHashSet {
    class MyHashSet {

        private int buckets = 1000;
        private int itemsPerBucket = 1001;
        private boolean[][] table;

        /** Initialize your data structure here. */
        public MyHashSet() {
            table = new boolean[buckets][];
        }

        //计算哈希值（商对应桶组）
        public int hash(int key){
            return key / buckets;
        }

        //计算位置（余数对应桶号）
        public int pos(int key){
            return key % buckets;
        }

        public void add(int key) {
            int hashkey = hash(key);
            if(table[hashkey] == null){
                table[hashkey] = new boolean[itemsPerBucket];
            }
            table[hashkey][pos(key)] = true;
        }

        public void remove(int key) {
            int hashkey = hash(key);
            if(table[hashkey] != null){
                table[hashkey][pos(key)] = false;
            }
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            int hashkey = hash(key);
            return table[hashkey] != null && table[hashkey][pos(key)];
        }
    }

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
}
