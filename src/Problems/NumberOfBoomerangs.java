package Problems;

import java.util.HashMap;

/**
 *
 * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that
 * the distance between i and j equals the distance between i and k (the order of the tuple matters).
 * Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).
 * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 * 找到所有回旋镖的数量。你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。
 *
 */

public class NumberOfBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < points.length; i++){
            for(int j = 0; j < points.length; j++){
                if(j == i){
                    continue;
                }
                int distance = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
                if(map.containsKey(distance)){
                    result += map.get(distance) * 2;
                }
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }
            map.clear();
        }
        return result;
    }
}
