package Problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
 */

public class MajorityElement {

    //排序法
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }

    //哈希表
    public int majorityElement1(int[] nums) {
        if(nums.length == 1)
            return nums[0];

        HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            }
            else {
                if(counts.get(num) >= (nums.length >> 1))
                    return num;
                counts.put(num, counts.get(num)+1);
            }
        }
        return -1;
    }

    //Boyer-Moore投票算法
    //时间复杂度o(n)，空间复杂度o(1)
    public int majorityElement2(int[] nums){
        int count = 0;
        Integer candidate = null;   //用Integer对象可以令candidate初始值不等于任何实数

        //设定侯选数candidate，当票数count为0时更新candidate为当前的数字，票数+1。
        //出现与candidate相同的数字，票数+1，反之票数-1。
        //总体上看，每两个不同的数字会进行一次消去，最后剩下最多的众数
        for(int num : nums){
            candidate = count == 0 ? num : candidate;
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    /////////////////////////////////////// 题目众数改进为超过1/3的数（Leetcode229）///////////////////////////////////////
    //改进Boyer-Moore投票算法
    //时间复杂度o(n)，空间复杂度o(1)


    // 首先明确超过n/3的数最多只能有两个，因此需要两个candidate，A和B。
    // 遍历数组，可能情况有3种：
    // 1.出现与A或B相同的数字，则对应的票数+1
    // 2.数字不同，且A或B票数为0，则将当前数字替换为新的candidate
    // 3.数字不同且票数都不为0，则A,B票数均-1，进行消去
    // 遍历结束后选出两个数量最多的candidate，检查其出现次数是否大于n/3，满足则输出。（两个candidate不可能相同且同时大于1/3）
    public List<Integer> majorityElement3(int[] nums) {
        ArrayList<Integer> answer = new ArrayList<>();
        if(nums == null || nums.length == 0)
            return answer;

        //投票方法选出两个最多的候选人
        int countA = 0;
        int countB = 0;
        Integer candidateA = nums[0];
        Integer candidateB = nums[0];
        for(int num : nums){
            //num等于其中一个candidate
            if(num == candidateA){
                countA++;
            }
            else if(num == candidateB){
                countB++;
            }
            //num与candidate都不相同，根据count选择更新candidate（为0）或者消去一票
            else if(countA == 0){
                candidateA = num;
                countA = 1;
            }
            else if(countB == 0){
                candidateB = num;
                countB = 1;
            }
            else{
                countA--;
                countB--;
            }
        }

        //确认两个candidate票数是否超过1/3
        countA = 0;
        countB = 0;
        for(int num : nums){
            if(num == candidateA)
                countA++;
            else if(num == candidateB)
                countB++;
        }
        //两个candidate不可能相同且同时大于1/3
        if(countA > nums.length / 3)
            answer.add(candidateA);
        if(countB > nums.length / 3)
            answer.add(candidateB);

        return answer;
    }
}
