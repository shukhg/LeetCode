/*
（1）可以交易 K 次的状态转移方程
        dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
        dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
（2）注意有个特例，就是 K 特别大，超过数组的一半，会使得超出内存。其实这个时候就可以不管 k，直接套用交易任意次的代码
*/



class Solution {
    public int maxProfit(int max_k, int[] prices) {
        int len = prices.length;
        if(prices == null || len <= 1)  return 0;
        if (max_k > len / 2)  return maxProfit_k_inf(prices);
        int[][][] dp = new int[len][max_k + 1][2];
        for(int i = 0; i < len; i ++){
            for(int k = 1; k <= max_k; k ++){
                if(i == 0){
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[len - 1][max_k][0];
    }
    public int maxProfit_k_inf(int[] prices) {
        int len = prices.length;
        if(len == 0)    return 0;
        int[][] dp = new int[len][2];
        for(int i = 0; i < len; i ++){
            if(i == 0){
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }
}