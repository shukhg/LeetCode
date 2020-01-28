/*
（1）从二维数组中每个位置都进行 dfs，将遍历到的位置标记为已访问
（2）二维循环中调用了多少次的 dfs 就有多少个岛屿
（3）还可以不开 flag数组，直接用原始的数组，如果遍历了的就标记为 '0'，这样就可以不开新数组了
*/


// 直接在原数组中修改
class Solution {
    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int result = 0;
        for(int i = 0; i < grid.length; i ++){
            for(int j = 0; j < grid[0].length; j ++){
                if(grid[i][j] == '1'){
                    result ++;
                    dfs(grid, i, j);
                }
            }
        }
        return result;
    }
    public void dfs(char[][] grid, int row, int col){
        if(row >= grid.length || row < 0 || col >= grid[0].length || col < 0 ){
            return ;
        }
        if(grid[row][col] != '1'){
            return ;
        }
        grid[row][col] = '0';
        dfs(grid, row + 1, col);
        dfs(grid, row - 1, col);
        dfs(grid, row, col + 1);
        dfs(grid, row, col - 1);
    }
}




// 新开一个 flag 数组用来表示每个位置是否被遍历过
class Solution {
    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        boolean[][] flag = new boolean[grid.length][grid[0].length];
        int result = 0;
        for(int i = 0; i < grid.length; i ++){
            for(int j = 0; j < grid[0].length; j ++){
                if(flag[i][j] == false && grid[i][j] == '1'){
                    result ++;
                    dfs(grid, flag, i, j);
                }
            }
        }
        return result;
    }
    public void dfs(char[][] grid, boolean[][] flag, int row, int col){
        if(row >= grid.length || row < 0 || col >= grid[0].length || col < 0 ){
            return ;
        }
        if(flag[row][col] == true || grid[row][col] != '1'){
            return ;
        }
        flag[row][col] = true;
        dfs(grid, flag, row + 1, col);
        dfs(grid, flag, row - 1, col);
        dfs(grid, flag, row, col + 1);
        dfs(grid, flag, row, col - 1);
    }
}