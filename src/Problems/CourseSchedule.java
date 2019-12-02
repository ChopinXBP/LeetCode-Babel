package Problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
 *
 */

public class CourseSchedule {
    //拓扑排序
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0){
            return true;
        }
        //遍历每一条边，计算所有结点的入度，将边保存在map中
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int[] inDegree = new int[numCourses];
        for(int[] p : prerequisites){
            inDegree[p[0]]++;
            map.putIfAbsent(p[1], new ArrayList<>());
            map.get(p[1]).add(p[0]);
        }
        //在优先队列中加入入度为0的结点
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }
        //result为拓扑排序结果，每次将入度为0的结点（当前起始结点）放入队列，每次弹出结点并删去与结点相邻的下一边（相邻结点入度-1）
        ArrayList<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()){
            int curNum = queue.pollFirst();
            result.add(curNum);
            //提前退出
            if(result.size() > numCourses){
                return false;
            }
            //每次将curNum结点的下一结点的出度-1，相当于删去拓扑排序的下一条边，当出现入度为0的结点时，加入队列。
            ArrayList<Integer> nextNums = map.get(curNum);
            if(nextNums != null){
                for(int nextNum : nextNums){
                    if(--inDegree[nextNum] == 0){
                        queue.add(nextNum);
                    }
                }
            }
        }
        //当且仅当拓扑排序结果的结点数与原数目相符时可以通过
        return result.size() == numCourses;
    }

    //DFS
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0){
            return true;
        }
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int[] p : prerequisites){
            map.putIfAbsent(p[1], new ArrayList<>());
            map.get(p[1]).add(p[0]);
        }
        //0：未访问，1：DFS正在访问，2：已访问
        int[] marked = new int[numCourses];

        for(int i = 0; i < numCourses; i++){
            if(hasCircle(i, map, marked)){
                return false;
            }
        }
        return true;
    }

    private boolean hasCircle(int curNum, HashMap<Integer, ArrayList<Integer>> map, int[] marked){
        if(marked[curNum] != 0){
           return marked[curNum] == 1 ? true : false;
        }
        marked[curNum] = 1;
        ArrayList<Integer> nextNums = map.get(curNum);
        if(nextNums != null){
            for(int nextNum : nextNums){
                if(hasCircle(nextNum, map, marked)){
                    return true;
                }
            }
        }
        marked[curNum] = 2;
        return false;
    }

    //课程表 II
    //返回你为了学完所有课程所安排的学习顺序。
    //可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        //遍历每一条边，计算所有结点的入度，将边保存在map中
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int[] inDegree = new int[numCourses];
        for(int[] p : prerequisites){
            inDegree[p[0]]++;
            map.putIfAbsent(p[1], new ArrayList<>());
            map.get(p[1]).add(p[0]);
        }
        //在优先队列中加入入度为0的结点
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }
        //result为拓扑排序结果，每次将入度为0的结点（当前起始结点）放入队列，每次弹出结点并删去与结点相邻的下一边（相邻结点入度-1）
        ArrayList<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()){
            int curNum = queue.pollFirst();
            result.add(curNum);
            //提前退出
            if(result.size() > numCourses){
                return new int[0];
            }
            //每次将curNum结点的下一结点的出度-1，相当于删去拓扑排序的下一条边，当出现入度为0的结点时，加入队列。
            ArrayList<Integer> nextNums = map.get(curNum);
            if(nextNums != null){
                for(int nextNum : nextNums){
                    if(--inDegree[nextNum] == 0){
                        queue.add(nextNum);
                    }
                }
            }
        }
        //当且仅当拓扑排序结果的结点数与原数目相符时可以通过
        if(result.size() == numCourses){
            int[] scheduel = new int[numCourses];
            for(int i = 0; i < numCourses; i++){
                scheduel[i] = result.get(i);
            }
            return scheduel;
        }else{
            return new int[0];
        }
    }

    //DFS + 辅助栈
    //算法说明：https://leetcode-cn.com/problems/course-schedule-ii/solution/ke-cheng-biao-ii-by-leetcode/
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] marked = new int[numCourses];
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int[] p : prerequisites){
            map.putIfAbsent(p[1], new ArrayList<>());
            map.get(p[1]).add(p[0]);
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, map, marked, stack)) {
                return new int[0];
            }
        }

        //DFS通过则说明无环，且结点均在栈中
        int[] scheduel = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            scheduel[i] = stack.pop();
        }
        return scheduel;
    }

    private boolean dfs(int curNum, HashMap<Integer, ArrayList<Integer>> map, int[] marked, Stack<Integer> stack) {
        if(marked[curNum] != 0){
            return marked[curNum] == 1 ? true : false;
        }
        marked[curNum] = 1;
        ArrayList<Integer> nextNums = map.get(curNum);
        if(nextNums != null){
            for(int nextNum : nextNums){
                if(dfs(nextNum, map, marked, stack)){
                    return true;
                }
            }
        }
        marked[curNum] = 2;
        //在拓扑的最深处反向入栈
        stack.add(curNum);
        return false;
    }
}
