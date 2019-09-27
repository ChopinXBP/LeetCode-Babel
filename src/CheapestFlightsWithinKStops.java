import java.util.*;

/**
 * There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.
 * Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops.
 * If there is no such route, output -1.
 * 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。
 */

public class CheapestFlightsWithinKStops {

    public static void main(String[] args) {
        int[][] edges = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        System.out.println(findCheapestPrice(3, edges, 0, 2, 1));
        System.out.println(findCheapestPrice1(3, edges, 0, 2, 1));
        System.out.println(findCheapestPrice2(3, edges, 0, 2, 1));
    }

    //基于Dijsktra算法（最小堆 + BFS）
    //每次从优先队列中poll出一个当前最小花费的站, 如果该站为dst则直接返回

    //城市结点类
    static class Vertex implements Comparable<Vertex> {
        int id;
        int costFromSrc;        //截至当前的路长
        int idxFromSrc;         //截至当前的结点数

        Vertex(int id, int costFromSrc, int idxFromSrc) {
            this.id = id;
            this.costFromSrc = costFromSrc;
            this.idxFromSrc = idxFromSrc;
        }

        //用于比较，小者在前
        public int compareTo(Vertex v) {
            return this.costFromSrc - v.costFromSrc;
        }
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        //将所有边的关系保存在二维数组edge中
        int[][] edges = new int[n][n];
        for (int i = 0; i < flights.length; i++) {
            edges[flights[i][0]][flights[i][1]] = flights[i][2];
        }

        PriorityQueue<Vertex> minHeap = new PriorityQueue<>();
        minHeap.add(new Vertex(src, 0, 0));

        //cost[i]代表从src前往i站最低花费，step[i]代表从src前往i站中转站数
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        int[] steps = new int[n];
        Arrays.fill(steps, Integer.MAX_VALUE);
        steps[src] = 0;


        while (!minHeap.isEmpty()) {
            Vertex curCity = minHeap.poll();
            if (curCity.id == dst) {
                return curCity.costFromSrc;
            }
            //跳过步数超过K的结点
            if (curCity.idxFromSrc == K + 1) {
                continue;
            }
            //遍历curCity到所有结点的路长和步数并更新
            for (int i = 0; i < n; i++) {
                if (edges[curCity.id][i] != 0) {
                    int newcost = curCity.costFromSrc + edges[curCity.id][i];
                    int newstep = curCity.idxFromSrc + 1;
                    if (newcost < cost[i]) {
                        cost[i] = newcost;
                        minHeap.add(new Vertex(i, newcost, newstep));
                    } else if (newstep < steps[i]) {
                        steps[i] = newstep;
                        minHeap.add(new Vertex(i, newcost, newstep));
                    }
                }
            }
        }

        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }

    //改进版
    public static int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
        //将flights保存成图
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int[] f : flights) {
            if (!prices.containsKey(f[0])) prices.put(f[0], new HashMap<>());
            prices.get(f[0]).put(f[1], f[2]);
        }

        //创建优先队列保存路径结果（花费，当前结点，当前步数（倒数））
        //PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing((int[] o) -> o[0]));
        pq.add(new int[] {0, src, k + 1});

        while (!pq.isEmpty()) {
            int[] top = pq.remove();
            int price = top[0];
            int city = top[1];
            int stops = top[2];
            if (city == dst) return price;
            if (stops > 0) {
                //getOrDefault如果存在就返回city对应的value，否则返回默认值new HashMap<>()
                Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                for (int a : adj.keySet()) {
                    //每遍历一个结点，更新长度，减少一步
                    pq.add(new int[] {price + adj.get(a), a, stops - 1});
                }
            }
        }
        return -1;
    }

    //动态规划
    public static int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        //dp[i][k]表示经过k步到达站i的最短距离
        //将dp[src]设置为0，其余为Integer.MAX_VALUE
        int[][] dp = new int[n][K + 2];
        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        Arrays.fill(dp[src], 0);

        //相当于每次对所有飞机更新一步
        for (int k = 1; k <= K + 1; k++) {
            for (int[] flight : flights) {
                int srcflight = flight[0];
                int dstflight = flight[1];
                int length = flight[2];
                //经过k步到达dst的最短距离 = 经过k-1步到达src的距离 + 两者距离，若有更短的结果则进行更新
                if (dp[srcflight][k - 1] != Integer.MAX_VALUE)
                    dp[dstflight][k] = Math.min(dp[dstflight][k], dp[srcflight][k - 1] + length);
            }
        }

        return dp[dst][K+1] == Integer.MAX_VALUE ? -1 : dp[dst][K+1];
    }
}
