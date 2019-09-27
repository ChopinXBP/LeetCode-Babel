import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A car travels from a starting position to a destination which is target miles east of the starting position.
 * Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0] miles east of the starting position, and has station[i][1] liters of gas.
 * The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.  It uses 1 liter of gas per 1 mile that it drives.
 * When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
 * What is the least number of refueling stops the car must make in order to reach its destination?  If it cannot reach the destination, return -1.
 * Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.  If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
 * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
 * 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。
 * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
 * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
 * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
 * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
 */

public class MinimumNumberofRefuelingStops {
    public static void main(String[] args) {
        int[][] stations = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
        System.out.println(minRefuelStops1(100, 10, stations));
    }

    //回溯法（会超时）
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        return Solution(0, 0, target, startFuel, stations, -1);
    }

    public static int Solution(int location, int count, int target, int curfuel, int[][] stations, int laststaidx) {
        int maxdistance = location + curfuel;
        if (maxdistance >= target)
            return count;
        if (laststaidx == stations.length - 1 || maxdistance < stations[laststaidx + 1][0])
            return -1;

        int newloc = stations[laststaidx + 1][0];
        int chooseres = Solution(newloc, count + 1, target, curfuel - (newloc - location) + stations[laststaidx + 1][1], stations, laststaidx + 1);
        int abandonres = Solution(newloc, count, target, curfuel - (newloc - location), stations, laststaidx + 1);
        return chooseres > 0 && abandonres > 0 ? Math.min(chooseres, abandonres) : Math.max(chooseres, abandonres);
    }

    //贪心
    public static int minRefuelStops1(int target, int startFuel, int[][] stations) {
        //建立最大堆模拟后备箱
        Queue<Integer> priorityQueue = new PriorityQueue<>((Integer i1, Integer i2) -> Integer.compare(i2, i1));
        int currentFuel = startFuel;
        int times = 0;
        int currentPosition = 0;
        int stationsnums = stations.length;

        //在汽车当前油量无法到达终点，不断进行加油前进至一个最远可达的加油站
        while (currentFuel < target) {
            //将车子开至当前油量能够到达的最远加油站，将途径所有加油站的油装至后备箱
            while (currentPosition < stationsnums && stations[currentPosition][0] <= currentFuel) {
                priorityQueue.add(stations[currentPosition++][1]);
            }
            //如果没有途径加油站，汽车将无法到达终点
            if (priorityQueue.isEmpty())
                return -1;
            //贪心：将后备箱当前最多的一桶油给汽车加上，继续前进
            currentFuel += priorityQueue.poll();
            times++;
        }

        return times;
    }
}
