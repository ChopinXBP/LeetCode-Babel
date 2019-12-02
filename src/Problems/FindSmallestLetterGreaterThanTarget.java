package Problems;

/**
 *
 * Given a list of sorted characters letters containing only lowercase letters, and given a target letter target,
 * find the smallest element in the list that is larger than the given target.
 * Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
 * 给定一个只包含小写字母的有序数组letters 和一个目标字母 target，寻找有序数组里面比目标字母大的最小字母。
 * 数组里字母的顺序是循环的。举个例子，如果目标字母target = 'z' 并且有序数组为 letters = ['a', 'b']，则答案返回 'a'。
 *
 */

public class FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int begin = 0;
        int end = letters.length - 1;
        if(end == 0 || target >= letters[end]){
            return letters[0];
        }
        while(begin < end){
            int mid = (begin + end) >> 1;
            if(letters[mid] <= target){
                begin = mid + 1;
            }else{
                end = mid;
            }
        }
        return letters[begin];
    }
}
