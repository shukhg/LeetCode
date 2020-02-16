/*
（1）dp[i][j][k] 表示子区间 [0, i] 能够使用 j 个 0 和 k 个 1 的字符串的最大数量
（2）遍历全部的 01串，对应每一个 01串，做的事情：对于可以放得下的背包分为：不放，则查看原旧背包容量；放，则 1（当前01串）+ 变小后的背包容量 
（3）状态转移方程： dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeros][k - ones] + 1)
*/



public class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];

        for (int i = 1; i <= len; i++) {
            // 注意：有一位偏移
            int[] cnt = countZeroAndOne(strs[i - 1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    // 先把上一行抄下来
                    dp[i][j][k] = dp[i - 1][j][k];

                    int zeros = cnt[0];
                    int ones = cnt[1];

                    if (j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    }
                }
            }
        }
        return dp[len][m][n];
    }
    private int[] countZeroAndOne(String str) {    //  统计 0 和 1 的数量
        int[] cnt = new int[2];
        for (char c : str.toCharArray()) {
            cnt[c - '0']++;
        }
        return cnt;
    }
}