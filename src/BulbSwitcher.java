/**
 *
 * There are n bulbs that are initially off. You first turn on all the bulbs.
 * Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
 * For the i-th round, you toggle every i bulb. For the n-th round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
 * 初始时有n个灯泡关闭。 第 1 轮，你打开所有的灯泡。 第 2 轮，每两个灯泡你关闭一次。
 * 第 3 轮，每三个灯泡切换一次开关（如果关闭则开启，如果开启则关闭）。第i轮，每i个灯泡切换一次开关。
 * 对于第n轮，你只切换最后一个灯泡的开关。 找出n轮后有多少个亮着的灯泡。
 *
 */

public class BulbSwitcher {
    public int bulbSwitch(int n) {
        //每个数i必能分解成任意两个数的乘积（最少会有1*i），因此只有平方数会进行单次开关
        //因此只需要统计截止n的平方数个数即可。
        return (int)Math.sqrt(n);
    }
}
