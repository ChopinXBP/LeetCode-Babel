package Problems;

import java.util.*;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * 给出一个区间的集合，请合并所有重叠的区间。
 */

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2 || intervals[0].length == 0) {
            return intervals;
        }
        //按数组头元素升序排序（lambda表达式排序是真的慢）
        //Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        ArrayList<int[]> itvs = new ArrayList<>();
        for (int i = 0; i < intervals.length - 1; i++) {
            //寻找重叠区间
            if (intervals[i][1] >= intervals[i + 1][0]) {
                //排除完全重叠区间，如[1,4]与[2,3]
                intervals[i + 1][0] = intervals[i][0];
                intervals[i + 1][1] = intervals[i][1] > intervals[i + 1][1] ? intervals[i][1] : intervals[i + 1][1];
            } else {
                itvs.add(intervals[i]);
            }
        }
        itvs.add(intervals[intervals.length - 1]);
        return itvs.toArray(new int[0][]);
    }

    //输入输出不同版本
    public class Interval {
        int start;
        int end;
        Interval() {
            start = 0;
            end = 0;
        }
        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        Collections.sort(intervals, (a, b) -> a.start - b.start);

        LinkedList<Interval> merged = new LinkedList<Interval>();
        for (Interval interval : intervals) {
            if (merged.isEmpty() || merged.getLast().end < interval.start) {
                merged.add(interval);
            }
            else {
                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
            }
        }
        ArrayList<Interval> result = new ArrayList<>();
        result.addAll(merged);
        return result;
    }
}
