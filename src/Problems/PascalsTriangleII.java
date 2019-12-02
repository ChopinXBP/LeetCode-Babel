package Problems;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 * Note that the row index starts from 0.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 */

public class PascalsTriangleII {
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> result = new ArrayList<>(rowIndex + 1);
        result.add(1);
        for(int i = 1; i <= rowIndex; i++){
            for(int j = i - 1; j >= 1; j--){
                result.set(j, result.get(j - 1) + result.get(j));
            }
            result.add(1);
        }
        return result;
    }
}
