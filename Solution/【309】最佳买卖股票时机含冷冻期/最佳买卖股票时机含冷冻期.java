/*
（1）记得如果还有交易次数 k 的限制，则是三维 dp 数组，而且是要 k + 1，因为可以是 0-k 次。此外，需要再加一个内层循环遍历 0-k
（2）因为此题有冷冻期，所以 dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]) 只能 i-2 天卖出然后 i 天买入
（3）两个 if 和 continue 处理初始化的情况
（4）既然只用到了 i-2、i-1、i 则可以用变量代替，而不是用 dp 数组
（5）为什么 dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]) 中是 dp[i-2][0] 而不能是 dp[i-1][0]，难道不能理解为 i-1 天没有卖出吗？
    如果 i-1 天没有卖出，那就 dp[i-2][0] 与 dp[i-1][0] 相等了，所以是用 dp[i-2][0]
*/



class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0)    return 0;
        int[][] dp = new int[prices.length][2];
        for(int i = 0; i < prices.length; i ++){
            if(i == 0){
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            if(i == 1){  // 因为状态转移方程中有 i-2，所以单独处理 i = 0、1 的情况
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);  // 
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);  // 因为有冷冻期，所以只能从 i - 2 天卖，i 天买入
        }
        return dp[prices.length - 1][0];
    }
}

// 用变量代替 dp数组，但是用 dp数组显得更加直观
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0;   // 代表 dp[i-2][0]
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = temp;
        }
        return dp_i_0;
    }
}