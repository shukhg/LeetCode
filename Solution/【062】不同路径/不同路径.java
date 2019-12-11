/*
简单的动态规划，别看错题目就可以了

但是可以有更好的解法。
因为遍历的时候是一行一行遍历，其实只要用一个一维数组去存储就可以了。
dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
dp[i - 1][j] 其实就是上一行的 dp 数组中的数据
可以改为  dp[j] = dp[j] + dp[j - 1]   这里的  dp[j] 实现了上一行
*/


// 降低空间复杂度
class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[m];
        Arrays.fill(dp, 1);
        for(int i = 1; i < n; i ++){
            for(int j = 1; j < m; j ++){
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[m - 1];
    }
}




class Solution {
    public int uniquePaths(int cols, int rows) {
        if(cols == 0 || rows == 0)
            return 0;
        if(cols == 1 || rows == 1)
            return 1;
        int[][] dp = new int[rows][cols];
        for(int i = 0 ; i < rows ; i ++){
            dp[i][0] = 1;
        }
        for(int i = 0 ; i < cols ; i ++){
            dp[0][i] = 1;
        }
        for(int i = 1 ; i < rows ; i ++){
            for(int j = 1 ; j < cols ; j ++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[rows - 1][cols - 1];
    }
}