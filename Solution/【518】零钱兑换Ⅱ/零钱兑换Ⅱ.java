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
（4）初始化：amout == 0 则全 1，coins 选第一个的时候则将其整数倍 置 1
*/




class Solution {
    public int change(int amount, int[] coins) {
        if(coins == null || coins.length == 0){
            if(amount == 0) return 1;
            else    return 0;    
        }
        int[][] dp = new int[coins.length][amount + 1];
        for(int i = 0; i < coins.length; i ++){
            dp[i][0] = 1;
        }
        for(int j = coins[0]; j <= amount; j = j + coins[0]){
            dp[0][j] = 1;
        }
        for(int i = 1; i < coins.length; i ++){
            for(int j = 1; j <= amount; j ++){
                // for (int k = 0; j - k * coins[i] >= 0; k++) {     此处是复杂度更高的解法
                //     dp[i][j] += dp[i - 1][j - k * coins[i]];
                // }
                if(j - coins[i] >= 0){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
                }
                else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length - 1][amount];
    }
}