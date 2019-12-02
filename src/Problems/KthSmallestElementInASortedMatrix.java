package Problems;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 请注意，它是排序后的第k小元素，而不是第k个元素。
 *
 */

public class KthSmallestElementInASortedMatrix {
    //最大堆
    public int kthSmallest(int[][] matrix, int k) {
        int row = Math.min(matrix.length, k);
        int col = Math.min(matrix[0].length, k);
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(queue.size() < k){
                    queue.add(matrix[i][j]);
                }else if(matrix[i][j] < queue.peek()){
                    queue.poll();
                    queue.add(matrix[i][j]);
                }
            }
        }
        return queue.peek();
    }

    //二分查找
    public int kthSmallest2(int[][] matrix, int k) {
        int row = Math.min(matrix.length, k);
        int col = Math.min(matrix[0].length, k);
        int left = matrix[0][0];
        int right = matrix[row - 1][col - 1];

        while (left < right) {
            int mid = (left + right) >> 1;
            // 找出二维矩阵中<=mid的元素总个数
            int count = findNotBiggerThanMid(matrix, mid, row, col);

            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    // 以行为单位找，找到每一行最后一个不大于mid的数的下标rowMaxLoc，即知道每一行有rowMaxLoc+1个数不大于mid
    private int findNotBiggerThanMid(int[][] matrix, int mid, int row, int col) {
        int count = 0;
        int rowMaxLoc = col - 1;
        for(int i = 0; i < row; i++){
            while(rowMaxLoc >= 0 && matrix[i][rowMaxLoc] > mid){
                rowMaxLoc--;
            }
            count += rowMaxLoc + 1;
        }
        return count;
    }
}
