package Problems;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 */

public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        int idx = 1;
        while(idx <= numRows){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            for(int i = 1; i < idx - 1; i++){
                list.add(result.get(idx - 2).get(i - 1) + result.get(idx - 2).get(i));
            }
            if(idx++ > 1){
                list.add(1);
            }
            result.add(list);
        }
        return result;
    }
}
