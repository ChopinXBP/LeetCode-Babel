package Problems;

import java.util.*;

/**
 *
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * The same repeated number may be chosen from candidates unlimited number of times.
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 *
 */

public class CombinationSum {
    //动态规划：自前向后
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        HashSet<Integer> set = new HashSet<>();
        for(int num : candidates){
            if(num <= target){
                set.add(num);
            }
        }

        //dp[i]代表和为i的所有组合数，dp[i]由dp[j](j<i)的所有组合+j构成
        ArrayList<List<Integer>>[] dp = new ArrayList[target + 1];
        dp[0] = new ArrayList<>();
        dp[0].add(new ArrayList<>());
        for(int i = 1; i <= target; i++){
            dp[i] = new ArrayList<>();
            for(int j = 0; j < i; j++){
                if(set.contains(i - j)){
                    for(List<Integer> list : dp[j]){
                        ArrayList<Integer> newlist = new ArrayList<>();
                        newlist.addAll(list);
                        newlist.add(i - j);
                        dp[i].add(newlist);
                    }
                }
            }
        }

        HashSet<List<Integer>> temp = new HashSet<>();
        for(List<Integer> list : dp[target]){
            Collections.sort(list);
            temp.add(list);
        }
        ArrayList<List<Integer>> result = new ArrayList<>();
        result.addAll(temp);
        return result;
    }

    //剪枝回溯法
    public List<List<Integer>> combinationSum2(int[] candidates, int target){
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        Solution(target, 0, new LinkedList<>(), candidates, target, result);
        return result;
    }

    public void Solution(int curSum, int startIdx, LinkedList<Integer> list, int[] candidates, int target, List<List<Integer>> result){
        if(curSum == 0){
            result.add(new LinkedList<>(list));
            return;
        }
        //剪枝：1.递归遍历范围从当前数字开始，保证去重；2.差值小于0的分支提前舍弃
        for(int i = startIdx; i < candidates.length && curSum - candidates[i] >= 0; i++){
            list.add(candidates[i]);
            Solution(curSum - candidates[i], i, list, candidates, target, result);
            list.pollLast();
        }
    }
}
