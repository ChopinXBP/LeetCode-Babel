package Problems;

import java.util.Arrays;

/**
 *
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 */

public class MergeSortedArray {

    //从尾部开始归并
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n == 0)
            return;
        int n1 = m - 1;
        int n2 = n - 1;
        int i = m + n - 1;
        while(n1 >= 0 && n2 >= 0){
            if(nums1[n1] > nums2[n2]){
                nums1[i--] = nums1[n1--];
            }else{
                nums1[i--] = nums2[n2--];
            }
        }
        if(n1 < 0){
            while(i >= 0){
                nums1[i--] = nums2[n2--];
            }
        }
    }
}
