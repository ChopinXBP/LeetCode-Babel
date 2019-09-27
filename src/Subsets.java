import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 */

public class Subsets {

    //递归解法
    public List<List<Integer>> subsets(int[] nums) {
        HashSet<List<Integer>> result = new HashSet<>();
        result.add(new ArrayList<>());

        List<Integer> numslist = Arrays.stream(nums).boxed().collect(Collectors.toList());

        for(int i = 0; i < nums.length; i++){
            Solution(i, 0, numslist, result);
        }

        List<List<Integer>> res = new LinkedList<>();
        res.addAll(result);
        return res;
    }

    //递归每次删除一个结点
    public void Solution(int target,  int idx, List<Integer> list, HashSet<List<Integer>> result){
        if(target == idx){
            result.add(list);
            return;
        }

        for(int i = 0; i < list.size(); i++){
            List<Integer> newlist = new LinkedList<>(list);
            newlist.remove(i);
            Solution(target, idx + 1, newlist, result);
        }
    }

    //动态规划做法
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        //遇到一个数就把所有子集加上该数组成新的子集
        for(int i = 0; i < nums.length; i++){
            int size = result.size();
            for(int j = 0; j < size; j++){
                ArrayList<Integer> newlist = new ArrayList<>(result.get(j));
                newlist.add(nums[i]);
                //Collections.sort(newlist);      //若要求排序
                result.add(newlist);
            }
        }

        /*
        //多规则排序：要求按照size和大小进行排序
        Collections.sort(result, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> list1, List<Integer> list2) {
                if(list1.size() != list2.size()){
                    return list1.size() - list2.size();
                }
                else{
                    for(int i = 0; i < list1.size(); i++){
                        int comp = list1.get(i) - list2.get(i);
                        if(comp != 0)
                            return comp;
                    }
                    return 0;
                }
            }
        });
        */

        return result;
    }
}
