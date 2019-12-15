package Problems;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 * 我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 1 的整数。
 * 请你返回由 [low, high] 范围内所有顺次数组成的 有序 列表（从小到大排序）。
 *
 */

public class SequentialDigits {
    public List<Integer> sequentialDigits(int low, int high) {
        int[] nums = {0, 1, 12, 123, 1234, 12345, 123456, 1234567, 12345678, 123456789};
        int[] adds = {0, 1, 11, 111, 1111, 11111, 111111, 1111111, 11111111, 111111111};
        List<Integer> result = new ArrayList<>();
        int numLow = (low + "").length();
        int numHigh = (high + "").length();
        for(int i = numLow; i <= numHigh; i++) {
            int times = 10 - i;
            while(i == numLow && nums[i] < low){
                nums[i] += adds[i];
                times--;
            }
            while(times-- > 0 && nums[i] < high){
                result.add(nums[i]);
                nums[i] += adds[i];
            }
        }
        return result;
    }
}
