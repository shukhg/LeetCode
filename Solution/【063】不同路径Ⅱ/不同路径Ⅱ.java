/*
（1）注意处理第一个元素
（2）注意第一行和第一列遇到障碍的时候的处理
*/


class Solution {
    public int uniquePathsWithObstacles(int[][] matrix) {
        int rows = matrix.length , cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        if(matrix[0][0] == 1)
            return 0;
        dp[0][0] = 1;
        for(int i = 1 ; i < rows ; i ++){
            if(matrix[i][0] == 0){
                dp[i][0] = dp[i - 1][0];
            }
            else{
                dp[i][0] = 0;
            }
        }
        for(int i = 1 ; i < cols ; i ++){
            if(matrix[0][i] == 0){
                dp[0][i] = dp[0][i - 1];
            }
            else{
                dp[0][i] = 0;
            }
        }
        for(int i = 1 ; i < rows ; i ++){
            for(int j = 1 ; j < cols ; j ++){
                if(matrix[i][j] == 0){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
                else{
                    dp[i][j] = 0;
                }
            }
        }
        return dp[rows - 1][cols - 1];
    }
}