/*
（1）背包问题
（2）状态转移方程：dp[i] = min(dp[i - coins[j]]) + 1 此处的 coins[j] 需要遍历全部的 coins
（3）因为最小面值不一定为 1，所以初始化为 最大值
*/



class Solution {
    public int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length == 0 || amount < 0)   return -1;
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for(int i = 1; i <= amount; i ++){
            dp[i] = Integer.MAX_VALUE;
            for(int j = 0; j < coins.length; j ++){
                if(coins[j] <= i && dp[i - coins[j]] != Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE? -1 : dp[amount];
    }
}