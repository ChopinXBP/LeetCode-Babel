import java.util.HashMap;

/**
 *
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 */

public class SubarraySumEqualsK {
    //二维dp+前n项和：超出内存限制
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        //dp[i][j]代表sum[i,j]
        int[][] dp = new int[nums.length][nums.length];
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                if(i == 0){
                    dp[i][j] = j == 0 ? nums[i] : dp[i][j - 1] + nums[j];
                }else{
                    //前n项和公式：sum[i,j] = sum[0,j] - sum[0,i-1]
                    dp[i][j] = dp[0][j] - dp[0][i - 1];
                }
                result = dp[i][j] == k ? result + 1 : result;
            }
        }
        return result;
    }

    //一维dp+前n项和
    public int subarraySum2(int[] nums, int k) {
        int result = 0;
        //dp[i]代表sum[0,i]
        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                int sum = 0;
                if(i == 0){
                    dp[j] = j == 0 ? nums[j] : dp[j - 1] + nums[j];
                    sum = dp[j];
                }else{
                    //前n项和公式：sum[i,j] = sum[0,j] - sum[0,i-1]
                    sum = dp[j] - dp[i - 1];
                }
                result = sum == k ? result + 1 : result;
            }
        }
        return result;
    }

    //常数空间dp
    public int subarraySum3(int[] nums, int k) {
        int result = 0;
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            for(int j = i; j < nums.length; j++){
                sum += nums[j];
                result = sum == k ? result + 1 : result;
            }
        }
        return result;
    }

    //哈希表（最优）
    public int subarraySum4(int[] nums, int k) {
        int result = 0;
        //map用于存储已经累加的前n项和sum
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            //当前和为sum[0,j]，如果前n项和中有存在n个sum[0,i)=sum-k，则必存在n个sum[i,j]=sum[0,j]-sum[0,i)=k
            if(map.containsKey(sum - k)){
                result += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}
