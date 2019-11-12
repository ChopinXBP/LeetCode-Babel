import java.util.HashMap;

/**
 *
 * You are given K eggs, and you have access to a building with N floors from 1 to N.
 * Each egg is identical in function, and if an egg breaks, you cannot drop it again.
 * You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break,
 * and any egg dropped at or below floor F will not break.
 * Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).
 * Your goal is to know with certainty what the value of F is.
 * What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * 你的目标是确切地知道 F 的值是多少。
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 *
 */

public class SuperEggDrop {

    //递归（超时）
    public int superEggDrop2(int K, int N) {
        if(N < 2 || K == 1){
            return N;
        }
        int result = N;
        //在手握K个鸡蛋时从第i层楼[0,N]向下丢鸡蛋，会有两种可能：
        //1.鸡蛋摔碎，将剩余K-1个鸡蛋移动到楼[0,i-1]进行测递归测试。
        //2.鸡蛋没碎，将剩余K个鸡蛋移动到楼[i,N]进行测试，可将i层看成0层，对[0,N-i]进行递归测试。
        for(int i = 1; i <= N; i++){
            int curMin = Math.max(superEggDrop2(K - 1, i - 1), superEggDrop2(K, N - i)) + 1;
            result = curMin < result ? curMin : result;
        }
        return result;
    }

    //动态规划：二分搜索
    //观察递归公式result = min(max(superEggDrop(K - 1, i - 1), superEggDrop(K, N - i)) + 1)，其中i在[1.N]变化
    //而fun1=superEggDrop(K - 1, i - 1)随i单调增，fun2=superEggDrop(K, N - i)随i单调减，两条单调函数的交点即为两者最大值的最小取值点
    //因此可以对i在[1,N]上进行二分查找
    HashMap<Integer, Integer> map = new HashMap<>();
    public int superEggDrop3(int K, int N) {
        if (N == 0){
            return 0;
        }
        if (K == 1){
            return N;
        }
        //因为K<=100，可以设计一个key值的取值方法，将已经计算过的K，N组合存入哈希表避免重复计算
        int key = N * 1000 + K;
        if (map.containsKey(key)){
            return map.get(key);
        }

        int begin = 1;
        int end = N;
        while (begin + 1 < end) {
            int mid = (begin + end) >> 1;
            int lowVal = superEggDrop(K - 1, mid - 1);
            int highVal = superEggDrop(K, N - mid);

            if (lowVal < highVal){
                begin = mid;
            }
            else if (lowVal > highVal){
                end = mid;
            }
            else{
                end = mid;
                begin = mid;
            }

        }
        int minimum = 1 + Math.min(Math.max(superEggDrop(K - 1, begin - 1), superEggDrop(K, N - begin)),
                Math.max(superEggDrop(K - 1, end - 1), superEggDrop(K, N - end)));
        map.put(key, minimum);
        return minimum;
    }

    //动态规划：自底向上
    //观察转为动态规划的递归公式dp[i][j] = min(max(dp[i-1][loc-1], dp[i][j-loc]) + 1)，其中i在[1.j]变化，j在[1, N]变化
    //而fun1=dp[i-1][loc-1]随loc单调增，fun2=dp[i][j-loc]随loc单调减，两条单调函数的交点即为两者最大值的最小取值点
    //寻找loc的思路转换为while循环遍历查找
    public int superEggDrop4(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        for(int i = 1; i <= K; i++){
            int loc = 1;
            for(int j = 1; j <= N; j++){
                if(i == 1){
                    dp[i][j] = j;
                    continue;
                }
                //令left为loc-1时的函数值，right为loc时的函数值，循环遍历找到最小值的拐点
                //由于后序j值上限不断增大，loc拐点值只可能逐渐向右移动，以此可以简化后序查找
                while (loc < j){
                    int left = Math.max(dp[i - 1][loc - 1], dp[i][j - loc]);
                    int right = Math.max(dp[i - 1][loc], dp[i][j - loc - 1]);
                    if(left <= right){
                        break;
                    }
                    loc++;
                }
                dp[i][j] = Math.max(dp[i - 1][loc - 1], dp[i][j - loc]) + 1;
            }
        }
        return dp[K][N];
    }

    //动态规划（超时）：求能够测得的最少楼层
    public int superEggDrop5(int K, int N) {
        //dp[i][j]代表有i个鸡蛋和j次移动时一定能够测得的最少楼层数
        int[][] dp = new int[K + 1][N + 1];
        //没有鸡蛋时或没有移动次数时无法测试dp=0；只有1个鸡蛋但有j次移动，dp=j（从0层开始逐层测试）
        for (int i = 1; i <= N; i++) {
            dp[1][i] = i;
            dp[0][i] = 0;
        }
        for (int i = 1; i <= K; i++) {
            dp[i][0] = 0;
        }

        for (int i = 2; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                //有i个鸡蛋和j次移动时，前j-1次移动的测试结果可能由多种测试路径得来，将其划分为k次和(j-1)-k次遍历取最大
                //第j-1次移动鸡蛋碎的结果为dp[i-1][k]，鸡蛋未碎的结果为dp[i][(j - 1) - k]，当前楼层第j次移动的最少测试结果为1（摔碎）
                //dp[i][j] = max(dp[i - 1][k], dp[i][(j - 1) - k]) + 1
                int tMinDrop = N * N;
                for (int k = 0; k < j; k++) {
                    tMinDrop = Math.min(tMinDrop, Math.max(dp[i - 1][k], dp[i][(j - 1) - k]) + 1);
                }
                dp[i][j] = tMinDrop;
            }
        }

        return dp[K][N];
    }

    //动态规划（次优）：求能够测得的最多楼层
    public int superEggDrop6(int K, int N) {
        //dp[i][j]代表i个鸡蛋在j次移动内能测试确定的最多楼层数
        int[][] dp = new int[N + 1][K + 1];
        //从第一层楼开始更新dp数组
        for(int i = 1; i <= N; i++){
            //0次移动无法进行测试，初始值dp[i][0]=0
            dp[i][0] = 0;
            for(int j = 1; j <= K; j++){
                //每多一次移动，可以多进行一次测试。在手中有i-1个鸡蛋的第j-1次移动时已经测出楼高dp[i-1][j-1]，
                //一次移动最差情况也可以测出当前楼层的情况，因此逆推出当前能够保证第j次移动得出结果的测试楼层为dp[i-1][j-1]+1
                //第j次移动鸡蛋摔碎，可以确定当前层（1层）的结果（碎），剩余i-1个鸡蛋和j-1次移动能得出dp[i-1][j-1]层
                //h1 = dp[i][j] = dp[i-1][j-1] + 1;
                //第j次移动鸡蛋未碎，可以确定前dp[i-1][j-1]+1层的结果（不碎）,剩余i个鸡蛋和j-1次移动能得出dp[i][j-1]层
                //h2 = dp[i][j] = dp[i-1][j-1] + 1 + dp[i-1][j];
                //dp = h2 >= max(h1, h2)
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1] + 1;
                if(dp[i][j] >= N){
                    return i;
                }
            }
        }
        return N;
    }

    //数学（最优）
    //最差情况下（一个鸡蛋）用N次移动可以测得N层楼高，因此可以移动次数x必在[1,N]之间，可以对x进行二分查找
    public int superEggDrop(int K, int N) {
        int begin = 1;
        int end = N;
        while(begin < end){
            int mid = (begin + end) >> 1;
            if(fun(mid, K, N) < N){
                begin = mid + 1;
            }else {
                end = mid;
            }
        }
        return begin;
    }

    //递归函数fun(x)代表x次移动能够测试的最大楼高
    //https://leetcode-cn.com/problems/super-egg-drop/solution/ji-dan-diao-luo-by-leetcode/
    //https://leetcode-cn.com/problems/super-egg-drop/solution/shu-xue-fa-jie-shi-by-lycao/
    private int fun(int x, int k, int n){
        int result = 0;
        int r = 1;
        for(int i = 1; i <= k; ++i){
            r *= x - i + 1;
            r /= i;
            result += r;
            if(result >= n){
                break;
            }
        }
        return result;
    }
}
