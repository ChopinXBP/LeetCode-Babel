import java.util.*;

/**
 *
 * https://leetcode.com/problems/the-skyline-problem/
 * https://leetcode-cn.com/problems/the-skyline-problem/
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。现在，假设您获得了城市风光照片（图A）上显示的所有建筑物的位置和高度，请编写一个程序以输出由这些建筑物形成的天际线（图B）。
 * 例如，图A中所有建筑物的尺寸记录为：[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] 。
 * 输出是以 [ [x1,y1], [x2, y2], [x3, y3], ... ] 格式的“关键点”（图B中的红点）的列表，它们唯一地定义了天际线。关键点是水平线段的左端点。
 * 请注意，最右侧建筑物的最后一个关键点仅用于标记天际线的终点，并始终为零高度。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 * 图B中的天际线应该表示为：[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]。
 *
 */

public class TheSkylineProblem {
    //归并算法
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int len = buildings.length;
        List<List<Integer>> result = new ArrayList<>();
        if(len == 0){
            return result;
        }

        //只有一栋楼时，返回该楼左上角顶点和右下角顶点作为天际线坐标
        if(len == 1){
            result.add(new ArrayList<Integer>(){{add(buildings[0][0]); add(buildings[0][2]);}});
            result.add(new ArrayList<Integer>(){{add(buildings[0][1]); add(0);}});
            return result;
        }

        //将问题转换为左右两个子问题的归并
        List<List<Integer>> left = getSkyline(Arrays.copyOfRange(buildings, 0, len >> 1));
        List<List<Integer>> right = getSkyline(Arrays.copyOfRange(buildings, len >> 1, len));
        return mergeSkylines(left, right);
    }

    //左右子问题的所有天际线结点归并，归并思想类似排序，将所有天际线结点按横坐标从小到大归并排序
    public List<List<Integer>> mergeSkylines(List<List<Integer>> left, List<List<Integer>> right){
        int pLeft = 0, pRight = 0;
        int preY = 0, leftY = 0, rightY = 0;
        List<List<Integer>> output = new ArrayList<>();

        //归并左右天际线的所有结点，将横坐标从小到大进行排序，每次选定一个较小的横坐标，更新其纵坐标值（取两楼较大者）
        while(pLeft < left.size() && pRight < right.size()){
            List<Integer> pointLeft = left.get(pLeft);
            List<Integer> pointRight = right.get(pRight);
            int newX, newY;
            if(pointLeft.get(0) < pointRight.get(0)){
                newX = pointLeft.get(0);
                leftY = pointLeft.get(1);
                pLeft++;
            }else{
                newX = pointRight.get(0);
                rightY = pointRight.get(1);
                pRight++;
            }
            newY = leftY > rightY ? leftY : rightY;
            //如果当前的y值与上一个天际线点的y值相同，说明该点对应的天际线在前楼投影的内部，不能构成天际线结点
            if(preY != newY){
                updateOutput(output, newX, newY);
                preY = newY;
            }
        }

        appendSkyline(output, left, pLeft, left.size(), preY);
        appendSkyline(output, right, pRight, right.size(), preY);
        return output;
    }

    //在结果中加入天际线结点（x,y），如果结点的x值与上一个结点的x相同，则更新y值
    public void updateOutput(List<List<Integer>> output, int x, int y){
        if(output.isEmpty() || output.get(output.size() - 1).get(0) != x){
            output.add(new ArrayList<Integer>(){{add(x); add(y);}});
        }else{
            output.get(output.size() - 1).set(1, y);
        }
    }

    //将剩余序列中不在前楼投影内部的结点归并入结果
    public void appendSkyline(List<List<Integer>> output, List<List<Integer>> skyline, int loc, int len, int preY){
        while(loc < len){
            List<Integer> point = skyline.get(loc++);
            if(preY != point.get(1)){
                updateOutput(output, point.get(0), point.get(1));
                preY = point.get(1);
            }
        }
    }

    //动态规划法
    //https://briangordon.github.io/2014/08/the-skyline-problem.html
    public List<List<Integer>> getSkyline2(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> height = new ArrayList<>();
        //将所有结点高度保存至序列，用负正来区分左右端点
        for(int[] b : buildings){
            height.add(new ArrayList<Integer>(){{add(b[0]); add(-b[2]);}});
            height.add(new ArrayList<Integer>(){{add(b[1]); add(b[2]);}});
        }
        //将所有结点按x正序与y正序排序，对于x相同的点，高度较大的点排在较前
        Collections.sort(height, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                if(o1.get(0).equals(o2.get(0))){
                   return o1.get(1) - o2.get(1);
                }
                return o1.get(0) - o2.get(0);
            }
        });

        //建立最大堆，将0入堆。（可以用红黑树TreeMap优化remove过程，用计数器标识相同高度即可）
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        queue.add(0);
        int pre = 0;
        for(List<Integer> h : height){
            //如果遍历到左端点，将楼高入队；如果遍历到右端点，则移除该高度（该楼遍历结束）
            if(h.get(1) < 0){
                queue.add(-h.get(1));
            }else{
                queue.remove(h.get(1));
            }
            //如果当前最大楼高与之前的楼高不相等，作为天际点加入结果。如果当前楼遍历结束，则cur等于0且与pre不同，会将最右侧的(x, 0)加入结果。
            int cur = queue.peek();
            if(pre != cur){
                result.add(new ArrayList<Integer>(){{add(h.get(0)); add(cur);}});
                pre = cur;
            }
        }

        return result;
    }

    //TreeMap改进版
    public List<int[]> getSkyline3(int[][] buildings) {
        List<int[]> heights = new ArrayList<>();
        for (int[] b : buildings) {
            heights.add(new int[]{b[0], -b[2]});
            heights.add(new int[]{b[1], b[2]});
        }
        Collections.sort(heights, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>(Collections.reverseOrder());
        heightMap.put(0, 1);
        int prevHeight = 0;
        List<int[]> skyLine = new LinkedList<>();
        for (int[] h : heights) {
            if (h[1] < 0) {
                Integer cnt = heightMap.get(-h[1]);
                cnt = (cnt == null) ? 1 : cnt + 1;
                heightMap.put(-h[1], cnt);
            } else {
                Integer cnt = heightMap.get(h[1]);
                if (cnt == 1) {
                    heightMap.remove(h[1]);
                } else {
                    heightMap.put(h[1], cnt - 1);
                }
            }
            int currHeight = heightMap.firstKey();
            if (prevHeight != currHeight) {
                skyLine.add(new int[]{h[0], currHeight});
                prevHeight = currHeight;
            }
        }
        return skyLine;
    }
}
