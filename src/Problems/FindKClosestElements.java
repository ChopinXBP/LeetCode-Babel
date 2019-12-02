package Problems;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array.
 * The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 * 给定一个排序好的数组，两个整数 k 和 x，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 * 如果有两个数与 x 的差值一样，优先选择数值较小的那个数。
 *
 */

public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if(k <= 0){
            return new LinkedList<>();
        }
        int begin = 0;
        int end = arr.length - 1;
        while(begin < end){
            int mid = (begin + end) >> 1;
            if(arr[mid] == x){
                begin = mid;
                break;
            }else if(arr[mid] < x){
                begin = mid + 1;
            }else{
                end = mid;
            }
        }
        begin = begin - 1 >= 0 && Math.abs(arr[begin - 1] - x) < Math.abs(arr[begin] - x) ? begin - 1 : begin;
        LinkedList<Integer> result = new LinkedList<>();
        result.add(arr[begin]);
        int left = begin;
        int right = begin;
        while(result.size() < k){
            if(left - 1 < 0){
                result.add(arr[++right]);
            } else if(right + 1 >= arr.length || Math.abs(arr[left - 1] - x) <= Math.abs(arr[right + 1] - x)){
                result.addFirst(arr[--left]);
            }else{
                result.add(arr[++right]);
            }
        }
        return result;
    }
}
