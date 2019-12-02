package Problems;

import java.util.Random;

/**
 *
 * Shuffle a set of numbers without duplicates.
 * 打乱一个没有重复元素的数组。
 *
 */

public class ShuffleAnArray {
    //洗牌问题：Fisher-Yates 洗牌算法
    class Solution {
        private int[] array;
        private int[] original;
        Random random;

        public Solution(int[] nums) {
            random = new Random();
            array = nums;
            original = nums.clone();
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            array = original.clone();
            return array;
        }

        /** Returns a random shuffling of the array. */
        //Fisher-Yates 洗牌算法：遍历每一个元素i，将i与i之后的一个随机元素j互换
        public int[] shuffle() {
            for(int i = 0; i < array.length; i++){
                swapArray(i, randRange(i, array.length));
            }
            return array;
        }

        private int randRange(int min, int max){
            return random.nextInt(max - min) + min;
        }

        private void swapArray(int i, int j){
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
}
