package Problems;

/**
 *
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array.
 * When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 * Notice that you can not jump outside of the array at any time.
 * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
 * 请你判断自己是否能够跳到对应元素值为 0 的 任意 下标处。
 * 注意，不管是什么情况下，你都无法跳到数组之外。
 *
 */

public class JumpGameIII {
    public boolean canReach(int[] arr, int start) {
        return backtrack(start, arr, new boolean[arr.length]);
    }

    public boolean backtrack(int start, int[] arr, boolean[] isVisited){
        if(start < 0 || start >= arr.length || isVisited[start]){
            return false;
        }
        if(arr[start] == 0){
            return true;
        }
        isVisited[start] = true;
        return backtrack(start + arr[start], arr, isVisited.clone()) || backtrack(start - arr[start], arr, isVisited.clone());
    }
}
