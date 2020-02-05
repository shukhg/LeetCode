/*
（1）状态定义   dp[i][j]：硬币列表的前缀子区间 [0, i] 能够凑成总金额 j 的组合数
（2）状态转移方程：因此这个  
     dp[i][j] = dp[i - 1][j - 0 * coins[i]] + 
                dp[i - 1][j - 1 * coins[i]] +
                dp[i - 1][j - 2 * coins[i]] + 
                ... + 
                dp[i - 1][j - k * coins[i]]
（3）更高效版状态转移方程  dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]]
    这个状态转移方程可以理解为 递归
*/


public class Solution {

    public int change(int amount, int[] coins) {
        int len = coins.length;
        if (len == 0) {
            if (amount == 0) {
                return 1;
            }
            return 0;
        }
        int[][] dp = new int[len][amount + 1];
        dp[0][0] = 1;

        for (int i = coins[0]; i <= amount; i += coins[0]) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= amount; j++) {
                // for (int k = 0; j - k * coins[i] >= 0; k++) {     此处是复杂度更高的解法
                //     dp[i][j] += dp[i - 1][j - k * coins[i]];
                // }
                dp[i][j] = dp[i - 1][j];
                if (j - coins[i] >= 0) {
                    dp[i][j] += dp[i][j - coins[i]];
                }
            }
        }
        return dp[len - 1][amount];
    }
}