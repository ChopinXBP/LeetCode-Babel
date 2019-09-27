import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Random;

/**
 *
 * Design a data structure that supports all following operations in average O(1) time.
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
 * 注意: 允许出现重复元素。
 * insert(val)：当元素 val 不存在时，向集合中插入该项。
 * remove(val)：元素 val 存在时，从集合中移除该项。
 * getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 *
 */

public class InsertDeleteGetRandom {
    //元素不重复
    class RandomizedSet {

        //ArrayList用于存放元素，HashMap用于存放元素及其在ArrayList中的位置。
        ArrayList<Integer> nums;
        HashMap<Integer, Integer> locs;
        Random random;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            nums = new ArrayList<>();
            locs = new HashMap<>();
            random = new Random();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(!locs.containsKey(val)){
                return false;
            }
            locs.put(val, nums.size());
            nums.add(val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        //若待删除元素val不在ArrayList的末尾，则将其与末尾元素交换，再进行删除，由此保证o（1）的删除效率
        public boolean remove(int val) {
            if(!locs.containsKey(val)){
                return false;
            }
            int curLoc = locs.get(val);
            if(curLoc != nums.size() - 1){
                int lastValue = nums.get(nums.size() - 1);
                nums.set(curLoc, lastValue);
                locs.put(lastValue, curLoc);
            }
            locs.remove(val);
            nums.remove(nums.size() - 1);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return nums.size() > 0 ? nums.get(random.nextInt(nums.size())) : Integer.MIN_VALUE;
        }
    }

    //允许元素重复
    class RandomizedCollection {

        //ArrayList用于存放元素，HashMap+LinkedHashSet用于存放重复元素在ArrayList中的位置。
        ArrayList<Integer> nums;
        HashMap<Integer, LinkedHashSet<Integer>> locs;
        Random random;

        /** Initialize your data structure here. */
        public RandomizedCollection() {
            nums = new ArrayList<>();
            locs = new HashMap<>();
            random = new Random();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            boolean contain = locs.containsKey(val);
            if(!contain){
                locs.put(val, new LinkedHashSet<>());
            }
            locs.get(val).add(nums.size());
            nums.add(val);
            return !contain;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        //若待删除元素val不在ArrayList的末尾，则将其与末尾元素交换，再进行删除，由此保证o（1）的删除效率
        public boolean remove(int val) {
            if(!locs.containsKey(val)){
                return false;
            }

            int curLoc = locs.get(val).iterator().next();
            //为防止末尾元素lastValue与当前元素val相同，先移除当前元素在LinkedHashSet中的下标curLoc
            locs.get(val).remove(curLoc);
            if (curLoc != nums.size() - 1) {
                int lastValue = nums.get(nums.size() - 1);
                nums.set(curLoc, lastValue);
                locs.get(lastValue).remove(nums.size() - 1);
                //若不先移除元素下标curLoc，则lastValue与val相同时无法添加
                locs.get(lastValue).add(curLoc);
            }
            nums.remove(nums.size() - 1);
            if(locs.get(val).isEmpty()){
                locs.remove(val);
            }

            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return nums.size() > 0 ? nums.get(random.nextInt(nums.size())) : Integer.MIN_VALUE;
        }
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
