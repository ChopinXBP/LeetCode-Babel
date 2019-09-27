import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 *
 */

public class LargestNumber {
    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++){
            strs[i] = nums[i] + "";
        }
        Arrays.sort(strs, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                return (s2 + s1).compareTo(s1 + s2);
            }
        });
        StringBuilder result = new StringBuilder();
        for(String s : strs){
            result.append(s);
        }
        return strs[0].equals("0") ? "0" : result.toString();
    }
}
