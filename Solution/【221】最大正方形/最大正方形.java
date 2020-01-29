/*
（1）看到 最大、最小 这种关键词，应该首先考虑动态规划
（2）如果是二维数组的动态规划，大部分情况下 dp[i][j] 是表示以 matrix[i][j] 位置为结尾的情况
（3）当我们判断以某个点为正方形右下角时最大的正方形时，那它的上方，左方和左上方三个点也一定是某个正方形的右下角，否则该点为右下角的正方形最大就是它自己了。这是定性的判断，那具体的最大正方形边长呢？我们知道，该点为右下角的正方形的最大边长，最多比它的上方，左方和左上方为右下角的正方形的边长多1，最好的情况是是它的上方，左方和左上方为右下角的正方形的大小都一样的，这样加上该点就可以构成一个更大的正方形。 但如果它的上方，左方和左上方为右下角的正方形的大小不一样，合起来就会缺了某个角落，这时候只能取那三个正方形中最小的正方形的边长加1了
（4）所以，dp[i][j] 表示以 matrix[i][j] 为右下角的正方形的最大边长
（5）状态转移方程为  dp(i, j) = min(dp(i−1, j), dp(i−1, j−1), dp(i, j−1)) + 1
（6）因为只用到了上一行的两个元素以及左边的元素，所以其实可以不用二维 dp 数组，而是用一维 dp 数组代替
*/



class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        int maxLen = 0;
        for(int i = 0; i < matrix.length; i ++){
            if(matrix[i][0] == '1'){
                dp[i][0] = 1;
                maxLen = 1;
            }
        }
        for(int j = 0; j < matrix[0].length; j ++){
            if(matrix[0][j] == '1'){
                dp[0][j] = 1;
                maxLen = 1;
            }
        }
        for(int i = 1; i < matrix.length; i ++){
            for(int j = 1; j < matrix[0].length; j ++){
                if(matrix[i][j] == '1'){
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen * maxLen;
    }
}