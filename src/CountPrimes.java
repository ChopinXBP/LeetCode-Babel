/**
 *
 * Count the number of prime numbers less than a non-negative number, n.
 * 统计所有小于非负整数 n 的质数的数量。
 *
 */

public class CountPrimes {
    //质数定义（超时）
    public int countPrimes(int n) {
        int result = 0;
        int num = 2;
        while(num <= n){
            int sqrtNum = (int)Math.sqrt(num);
            result++;
            for(int i = 2; i <= sqrtNum; i++){
                if(num % i == 0){
                    result--;
                    break;
                }
            }
            num++;
        }
        return result;
    }

    //厄拉多塞算法
    //将2-n放入表中，每次从头遍历每一个元素i，将i的倍数从表中删去，最后剩下的数即是质数。
    public int countPrimes2(int n){
        boolean[] primeNum = new boolean[n];
        int result = 0;
        for(int i = 2; i < n; i++){
            if(primeNum[i] == false){
                result++;
                for(int times = 2; i * times < n; times++){
                    primeNum[i * times] = true;
                }
            }
        }
        return result;
    }
}
