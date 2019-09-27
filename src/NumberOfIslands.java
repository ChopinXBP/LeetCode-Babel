/**
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 *
 */

public class NumberOfIslands {
    //DFS
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        boolean[][] isVisited = new boolean[grid.length][grid[0].length];
        int result = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1' && !isVisited[i][j]){
                    Solution(grid, isVisited, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    public void Solution(char[][] grid, boolean[][] isVisited, int x, int y){
        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || isVisited[x][y] || grid[x][y] == '0'){
            return;
        }
        isVisited[x][y] = true;
        Solution(grid, isVisited, x - 1, y);
        Solution(grid, isVisited, x + 1, y);
        Solution(grid, isVisited, x, y - 1);
        Solution(grid, isVisited, x, y + 1);
    }

    //并查集:遍历二维网格，将竖直或水平相邻的陆地联结。最终，返回并查集数据结构中相连部分的数量。
    class UnionFind{
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid){
            count = 0;
            int row = grid.length;
            int col = grid[0].length;
            parent = new int[row * col];
            rank = new int[row * col];
            for(int i = 0; i < row; i++){
                for(int j = 0; j < col; j++){
                    if(grid[i][j] == '1'){
                        parent[i * col + j] = i * col + j;
                        count++;
                    }
                    rank[i * col + j] = 0;
                }
            }
        }

        public int find(int idx){
            if(parent[idx] != idx){
                parent[idx] = find(parent[idx]);
            }
            return parent[idx];
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY){
                if(rank[rootX] > rank[rootY]){
                    parent[rootY] = rootX;
                }
                else if(rank[rootX] < rank[rootY]){
                    parent[rootX] = rootY;
                }
                else{
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                count--;
            }
        }

        public int getCount(){
            return count;
        }
    }

    public int numIslands2(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }

        int row = grid.length;
        int col = grid[0].length;
        UnionFind unionFind = new UnionFind(grid);

        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                if(grid[r][c] == '1'){
                    grid[r][c] = '0';
                    if(r - 1 >= 0 && grid[r - 1][c] == '1'){
                        unionFind.union(r * col + c, (r - 1) * col + c);
                    }
                    if(r + 1 < row && grid[r + 1][c] == '1'){
                        unionFind.union(r * col + c, (r + 1) * col + c);
                    }
                    if(c - 1 >= 0 && grid[r][c - 1] == '1'){
                        unionFind.union(r * col + c, r * col + c - 1);
                    }
                    if(c + 1 < col && grid[r][c + 1] == '1'){
                        unionFind.union(r * col + c, r * col + c + 1);
                    }
                }
            }
        }

        return unionFind.getCount();
    }
}
