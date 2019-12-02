package Problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 */

public class Combinations {

    //回溯法
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if(k > n){
            return result;
        }
        for(int i = 1; i <= n - k + 1; i++){
            LinkedList<Integer> list = new LinkedList<>();
            list.add(i);
            Solution(n, k - 1, list, result);
            list.pollLast();
        }
        return result;
    }

    private void Solution(int n, int k, LinkedList<Integer> list, List<List<Integer>> result){
        if(k == 0){
            result.add(new LinkedList<>(list));
            return;
        }
        for(int i = list.getLast() + 1; i <= n; i++){
            list.add(i);
            Solution(n, k - 1, list, result);
            list.pollLast();
        }
    }

    //字典序
    public List<List<Integer>> combine2(int n, int k) {
        //将数字序列nums初始化为[1…k, n+1]，一共k+1个元素，最后一位n+1用于作为哨兵
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for(int i = 1; i <= k; i++){
            nums.add(i);
        }
        nums.add(n + 1);

        List<List<Integer>> result = new ArrayList<>();
        //循环将nums从最小排列[1…k, n+1]更新至最大排列[n-k…n-1, n+1]
        int idx = 0;
        while(idx < k){
            //每次添加前k个元素至结果数组（除去末尾哨兵元素）
            result.add(new ArrayList<>(nums.subList(0, k)));
            //遍历nums找出第一个与下一个数不连续的数字下标idx，将其数字加1（nums[idx]++）；idx之前的元素与其下标i一致（nums[i]=i+1）
            //当num更新至最大排列[n-k…n-1, n+1]时，数组连续，idx=k跳出循环
            idx = 0;
            while(idx < k && nums.get(idx + 1) == nums.get(idx) + 1){
                nums.set(idx, idx + 1);
                idx++;
            }
            nums.set(idx, nums.get(idx) + 1);
        }
        return result;
    }
}
