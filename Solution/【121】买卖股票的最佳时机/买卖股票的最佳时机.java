/*
只允许交易一次的话，那就一趟遍历，中间记录最小的元素 min 和最大的收益 max，要是遇到更小的元素就更新 min ， 遇到更大的收益就更新 max 即可
*/


/*
第一维是 第i天，第二维是剩余能够买卖的次数，第三维是当前是否有股票在手


base case：
dp[-1][k][0] = dp[i][0][0] = 0
dp[-1][k][1] = dp[i][0][1] = -infinity

状态转移方程：
dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

*/


// 套用框架
class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1)    return 0;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];  // 在 0 时刻就有股票了，所以定义为负值
        for(int i = 1; i < prices.length; i ++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);  // 当前时刻卖出
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);  // 因为只买入一次，所以这里是  -prices[i]
        }
        return dp[prices.length - 1][0];
    }
}


// 套用框架，但是只用两个数代替原先的 dp 数组
class Solution {
    int maxProfit(int[] prices) {
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }
}



class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1)    return 0;
        int min_price = prices[0];
        int result = 0;
        for(int i = 1; i < prices.length; i ++){
            if(prices[i] < min_price){
                min_price = prices[i];
            }
            if(prices[i] - min_price > result){
                result = prices[i] - min_price;
            }
        }
        return result;
    }
}