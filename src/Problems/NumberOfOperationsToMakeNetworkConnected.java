package Problems;

/**
 * There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b] represents a connection between computers a and b.
 * Any computer can reach any other computer directly or indirectly through the network.
 * Given an initial computer network connections. You can extract certain cables between two directly connected computers,
 * and place them between any pair of disconnected computers to make them directly connected. Return the minimum number of times you need to do this in order to
 * make all the computers connected. If it's not possible, return -1.
 * 用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。
 * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
 * 给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。
 */

public class NumberOfOperationsToMakeNetworkConnected {
    //并查集
    private class UnionFind {
        //父亲结点标识数组
        private int[] parent;
        //连通集个数
        private int count;

        //初始化并查集，所有节点的祖先均为其本身。连通集初始个数为n
        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        //find用于查找x节点的祖先节点（祖先节点的特征为x=parent[x]）
        public int find(int x) {
            while (x != parent[x]) {
                // 路径压缩（隔代压缩）
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        //合并x和y。如果 x 和 y 本来就在一个连通分量里，返回 false；否则合并x和y，连通集个数-1。
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false;
            }

            parent[rootX] = rootY;
            count--;
            return true;
        }
    }

    public int makeConnected(int n, int[][] connections) {
        // 建立并查集，每一个多余的连通集都需要一次操作进行消除，每一次操作需要一条多余的边。
        UnionFind unionFind = new UnionFind(n);

        // 计算多余的边的数量，每次失败的合并意味着有一条多余的边。
        int leftEdge = 0;
        for (int[] connection : connections) {
            boolean success = unionFind.union(connection[0], connection[1]);
            if (!success) {
                leftEdge++;
            }
        }

        // 计算多余的连通集个数result。
        // 如果result=0，说明已经全连通，直接返回0；如果leftEdge<result，说明多余的边不足以补齐完整连通集，返回-1。否则返回result。
        int result = unionFind.count - 1;
        return result == 0 ? 0 : (leftEdge < result ? -1 : result);
    }
}
