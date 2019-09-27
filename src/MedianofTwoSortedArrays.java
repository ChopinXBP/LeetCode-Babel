
/**
 * 
 * @author ChopinXBP
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.Find the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 延伸：求有序数组中第K小的数
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;

public class MedianofTwoSortedArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums1 = { 3 };
		int[] nums2 = { 1, 2 };
		System.out.println(findMedianSortedArrays(nums1, nums2));
		System.out.println(findMedianSortedArrays1(nums1, nums2));
		System.out.println(findMedianSortedArrays2(nums1, nums2));
		System.out.println(findMedianSortedArrays3(nums1, nums2));
	}

	// 在线处理，复杂度o(m+n)
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if (nums1.length == 0) {
			int length = nums2.length;
			if ((length & 0x01) == 1) {
				return (double) nums2[length / 2];
			} else {
				return (double) (nums2[length / 2 - 1] + nums2[length / 2]) / 2;
			}
		} else if (nums2.length == 0) {
			int length = nums1.length;
			if ((length & 0x01) == 1) {
				return (double) nums1[length / 2];
			} else {
				return (double) (nums1[length / 2 - 1] + nums1[length / 2]) / 2;
			}
		}

		int length1 = nums1.length;
		int length2 = nums2.length;
		int idx = (length1 + length2) / 2;

		int num1 = 0; // 记录数组1遍历数字个数
		int num2 = 0; // 记录数组2遍历数字个数
		int smaller;
		int bigger;
		if (nums1[0] < nums2[0]) {
			bigger = nums1[0];
			smaller = nums1[0];
			num1 = 1;
		} else {
			bigger = nums2[0];
			smaller = nums2[0];
			num2 = 1;
		}

		// 区分奇偶情况，循环运算次数不同
		int total;
		boolean flag;
		if (((length1 + length2) & 0x01) == 1) {
			total = idx + 1;
			flag = true;
		} else {
			total = idx;
			flag = false;
		}

		// 每次推进并更新smaller和bigger，令smaller位于中位数idx,bigger位于idx+1
		while (num1 + num2 <= total) {
			if (num1 < length1 && num2 < length2) {
				smaller = bigger;
				if (nums1[num1] < nums2[num2]) {
					bigger = nums1[num1]; // nums1[num1+1-1];
					num1++;
				} else {
					bigger = nums2[num2];
					num2++;
				}
			} else if (num1 == length1) {
				smaller = bigger;
				bigger = nums2[num2];
				num2++;
			} else if (num2 == length2) {
				smaller = bigger;
				bigger = nums1[num1];
				num1++;
			}
		}

		if (flag) {
			return smaller;
		} else {
			return (double) (smaller + bigger) / 2;
		}

	}

	// 归并，复杂度o(m+n)
	public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
		if (nums1.length == 0) {
			int length = nums2.length;
			if ((length & 0x01) == 1) {
				return (double) nums2[length / 2];
			} else {
				return (double) (nums2[length / 2 - 1] + nums2[length / 2]) / 2;
			}
		} else if (nums2.length == 0) {
			int length = nums1.length;
			if ((length & 0x01) == 1) {
				return (double) nums1[length / 2];
			} else {
				return (double) (nums1[length / 2 - 1] + nums1[length / 2]) / 2;
			}
		}

		int idx = (nums1.length + nums2.length) / 2;
		ArrayList<Integer> nums = new ArrayList<>();
		int loc1 = 0;
		int loc2 = 0;

		while (nums.size() <= idx) {
			if (loc1 == nums1.length) {
				nums.add(nums2[loc2]);
				loc2++;
			} else if (loc2 == nums2.length) {
				nums.add(nums1[loc1]);
				loc1++;
			} else if (nums1[loc1] < nums2[loc2]) {
				nums.add(nums1[loc1]);
				loc1++;
			} else {
				nums.add(nums2[loc2]);
				loc2++;
			}
		}

		if (((nums1.length + nums2.length) & 0x01) == 1) {
			return nums.get(idx);
		} else {
			return (double) (nums.get(idx - 1) + nums.get(idx)) / 2;
		}
	}

	// 循环二分，思路为从两个数组中找第K小的数，复杂度o(log(m+n))
	public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		if (m > n) { // to ensure m<=n
			int[] temp = nums1;
			nums1 = nums2;
			nums2 = temp;
			int tmp = m;
			m = n;
			n = tmp;
		}
		int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
		while (iMin <= iMax) {
			int i = (iMin + iMax) / 2;
			int j = halfLen - i;
			if (i < iMax && nums2[j - 1] > nums1[i]) {
				iMin = i + 1; // i is too small
			} else if (i > iMin && nums1[i - 1] > nums2[j]) {
				iMax = i - 1; // i is too big
			} else { // i is perfect
				int maxLeft = 0;
				if (i == 0) {
					maxLeft = nums2[j - 1];
				} else if (j == 0) {
					maxLeft = nums1[i - 1];
				} else {
					maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
				}
				if ((m + n) % 2 == 1) {
					return maxLeft;
				}

				int minRight = 0;
				if (i == m) {
					minRight = nums2[j];
				} else if (j == n) {
					minRight = nums1[i];
				} else {
					minRight = Math.min(nums2[j], nums1[i]);
				}

				return (maxLeft + minRight) / 2.0;
			}
		}
		return 0.0;
	}

	// 递归二分，思路为从两个数组中找第K小的数，复杂度o(log(m+n))
	public static double findMedianSortedArrays3(int[] nums1, int[] nums2) {
		int idx = (nums1.length + nums2.length) / 2;

		if (((nums1.length + nums2.length) & 0x01) == 1) {
			return findKth(nums1, nums2, idx + 1);
		} else {
			return (findKth(nums1, nums2, idx) + findKth(nums1, nums2, idx + 1)) / 2.0;
		}
	}

	// 求两排序数组第k小数
	public static double findKth(int[] nums1, int[] nums2, int k) {
		int length1 = nums1.length;
		int length2 = nums2.length;
		// 始终保持元素较少数组位于前面的位置
		if (length1 > length2) {
			return findKth(nums2, nums1, k);
		}
		// 若前一个数组为空，则直接返回后一个数组第k小数
		if (length1 == 0) {
			return nums2[k - 1];
		}
		// 若k为1，则返回两个数组头元素的较小值
		if (k == 1) {
			return nums1[0] < nums2[0] ? nums1[0] : nums2[0];
		}

		// 将nums1和nums2分别按loc1和loc2划分成两份，使得loc1与loc2的和为k
		int loc1 = k / 2 < length1 ? k / 2 : length1; // loc1取length1与k/2中的较小值
		int loc2 = k - loc1;

		// 若nums1第loc1小值比nums2第loc2小值要小，说明前loc1小值不存在第k小值，其出现在nums1的后半数组与nums2中，截取数组递归求解
		if (nums1[loc1 - 1] < nums2[loc2 - 1]) {
			int[] newarray = Arrays.copyOfRange(nums1, loc1, length1);
			return findKth(newarray, nums2, k - loc1);
		}
		// 反之同理
		else if (nums1[loc1 - 1] > nums2[loc2 - 1]) {
			int[] newarray = Arrays.copyOfRange(nums2, loc2, length2);
			return findKth(nums1, newarray, k - loc2);
		}
		// 当nums1第loc1小值与nums2第loc2小值相等时，返回该值
		else {
			return nums1[loc1 - 1];
		}
	}
}
