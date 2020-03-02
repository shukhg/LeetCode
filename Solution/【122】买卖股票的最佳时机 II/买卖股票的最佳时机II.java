/*
因为中间可以无数次买入和卖出，所以要理解只要后一天大于前一天，就可以买入。
*/


/*
第二维是代表当前是否有股票

dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
*/

// 框架写法
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for(int i = 0; i < len; i ++){
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }
}

// 标准写法
class Solution {
    public int maxProfit(int[] prices) {
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


// 贪心写法
class Solution {
    public int maxProfit(int[] prices) {
        int thisSum = 0;
        int maxSum = 0;
        for(int i = 1 ; i < prices.length ; i++){
            if(prices[i] > prices[i - 1]){
                maxSum = maxSum + prices[i] - prices[i - 1]; 
            }
        }
        return maxSum ;
    }
}