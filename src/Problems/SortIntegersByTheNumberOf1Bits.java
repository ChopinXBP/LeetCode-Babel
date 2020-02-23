package Problems;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * Given an integer array arr. You have to sort the integers in the array in ascending order by the number of 1's
 * in their binary representation and in case of two or more integers have the same number of 1's you have to sort them in ascending order.
 * Return the sorted array.
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 * 请你返回排序后的数组。
 *
 */

public class SortIntegersByTheNumberOf1Bits {
    class Node{
        int val;
        int num;
        Node(int v, int n){
            val = v;
            num = n;
        }
    }

    public int[] sortByBits(int[] arr) {
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> {
            return n1.num == n2.num ? (n1.val - n2.val) : (n1.num - n2.num);
        });
        for(int num : arr){
            queue.add(new Node(num, numOfBits(num)));
        }
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = queue.poll().val;
        }
        return result;
    }

    public int numOfBits(int num){
        int result = 0;
        while(num > 0){
            num &= (num - 1);
            result++;
        }
        return result;
    }
}
