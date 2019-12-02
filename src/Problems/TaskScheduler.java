package Problems;

import java.util.*;

/**
 *
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.
 * Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的最短时间。
 *
 */

public class TaskScheduler {
    //设计桶大小为n+1,相同的任务不能放入同一个桶，一个桶不管是否放满，占用时间至少n+1。
    //总排队时间 = (桶个数 - 1) * 桶大小(n + 1) + 最后一桶的任务数
    public int leastInterval(char[] tasks, int n) {
        int[] words = new int[26];
        for(char c : tasks){
            words[c - 'A']++;
        }
        //桶的个数就是重复次数最多的任务的个数
        int bucketNums = Integer.MIN_VALUE;
        for(int num : words){
            bucketNums = num > bucketNums ? num : bucketNums;
        }
        //最后一桶任务数为桶个数的重复任务数
        int lastTasks = 0;
        for(int num : words){
            lastTasks = num == bucketNums ? lastTasks + 1 : lastTasks;
        }
        return Math.max(tasks.length, (bucketNums - 1) * (n + 1) + lastTasks);
    }
}
