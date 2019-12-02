package Problems;

import java.util.Arrays;

/**
 *
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 * One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * Note: Rotation is not allowed.
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 说明: 不允许旋转信封。
 *
 */

public class RussianDollEnvelopes {
    //动态规划
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b)->a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        int result = 0;
        for(int i = 0; i < envelopes.length; i++) {
            for(int j = 0; j < i; j++) {
                if(envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(dp[i], result);
        }
        return result;
    }

    //动态规划+二分查找
    public int maxEnvelopes2(int[][] envelopes) {
        int len = envelopes.length;
        if (len < 2) {
            return len;
        }
        Arrays.sort(envelopes, (a, b)->a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        int[] tail = new int[len];
        tail[0] = envelopes[0][1];
        // end 表示有序数组 tail 的最后一个已经赋值元素的索引
        int end = 0;

        for (int i = 1; i < len; i++) {
            int target = envelopes[i][1];

            if (target > tail[end]) {
                end++;
                tail[end] = target;
            } else {
                int left = 0;
                int right = end;

                while (left < right) {
                    int mid = (left + right) >>> 1;
                    if (tail[mid] < target) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                tail[left] = target;
            }
        }
        return end + 1;
    }
}
