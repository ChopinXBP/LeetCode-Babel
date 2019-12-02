package Problems;

/**
 *
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
 * Note:
 * If there exists a solution, it is guaranteed to be unique.
 * Both input arrays are non-empty and have the same length.
 * Each element in the input arrays is a non-negative integer.
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * 说明: 
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 *
 */

public class GasStation {
    //计算每一个加油站的收益gas[i] - cost[i]，终点必为收益最小的加油站
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = -1;
        int minCost = Integer.MAX_VALUE;
        int lastCost = 0;
        for(int i = 0; i < gas.length; i++){
            lastCost += gas[i] - cost[i];
            if(lastCost < minCost){
                minCost = lastCost;
                start = i + 1;
            }
        }
        return lastCost < 0 ? -1 : (start == gas.length ? 0 : start);
    }
}
