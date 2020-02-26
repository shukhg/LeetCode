/*
（1）dp[i][j][fir or sec] 定义：开始的索引 i，结束的索引 j，当前轮到的人（fir 表示先手，sec 表示后手）
（2）状态转移方程：
    dp[i][j].fir = max(piles[i] + dp[i+1][j].sec, piles[j] + dp[i][j-1].sec)
    dp[i][j].fir = max(    选择最左边的石头堆     ,     选择最右边的石头堆     )
    if 先手选择左边:    dp[i][j].sec = dp[i+1][j].fir
    if 先手选择右边:    dp[i][j].sec = dp[i][j-1].fir
（3）初始化：dp[i][j].fir = piles[i]，dp[i][j].sec = 0，其中 0 <= i == j < n   我们发现初始化是 对角线
（4）遍历：dp[i][j] 依赖于 dp[i + 1][j] 和 dp[i][j - 1]，所以应该沿着与主对角线平行的方向进行 斜着遍历
*/



class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        // 初始化 dp 数组
        int[][][] dp = new int[n][n][2];   // 最后一维的 0 表示当前自己先手，1 表示自己后手

        // 填入 base case
        for (int i = 0; i < n; i++) {
            dp[i][i][0] = piles[i];
            dp[i][i][1] = 0;
        }
        // 斜着遍历数组
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = l + i - 1;
                // 先手选择最左边或最右边的分数
                int left = piles[i] + dp[i+1][j][1];
                int right = piles[j] + dp[i][j-1][1];
                // 套用状态转移方程
                if (left > right) {
                    dp[i][j][0] = left;
                    dp[i][j][1] = dp[i+1][j][0];
                } else {
                    dp[i][j][0] = right;
                    dp[i][j][1] = dp[i][j-1][0];
                }
            }
        }
        return dp[0][n-1][0] > dp[0][n-1][1];
    }
}