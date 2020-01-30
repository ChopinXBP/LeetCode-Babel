package Problems;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
 * Each LED represents a zero or one, with the least significant bit on the right.
 * For example, the above binary watch reads "3:25".
 * Given a non-negative integer n which represents the number of LEDs that are currently on,
 * return all possible times the watch could represent.
 * 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * 例如，上面的二进制手表读取 “3:25”。
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 *
 */

public class BinaryWatch {
    //遍历所有时间的二进制的1的个数
    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if(Integer.bitCount(i) + Integer.bitCount(j) == num){
                    result.add(String.format("%s:%02d", i, j));
                }
            }
        }
        return result;
    }

    //DFS
    public List<String> readBinaryWatch2(int num) {
        result = new ArrayList<>();
        Solution(num, 0, 0, 0);
        return result;
    }

    List<String> result;

    public void Solution(int num, int loc, int curHour, int curMin){
        if(curHour > 11 || curMin > 59){
            return;
        }
        if(num == 0){
            result.add(String.format("%s:%02d", curHour, curMin));
            return;
        }
        if(loc > 10){
            return;
        }
        if(loc < 4){
            Solution(num - 1, loc + 1, curHour + (int)Math.pow(2, loc), curMin);
        }else {
            Solution(num - 1, loc + 1, curHour, curMin + (int)Math.pow(2, loc - 4));
        }
        Solution(num, loc + 1, curHour, curMin);
    }
}
