/*
（1）背包问题
（2）状态转移方程：dp[i] = min(dp[i - coins[j]]) + 1 此处的 coins[j] 需要遍历全部的 coins
（3）因为最小面值不一定为 1，所以初始化为 最大值
*/


/*
（1）状态转移方程：dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i]] + 1) 
（2）上述方程需要注意下面两点
（3）需要保证 dp[i][j - coins[i]] 的有效性，也就是 j - coins[i] >= 0 并且 dp[i][j - coins[i]] != Integer.MAX_VALUE，
    之所以有后面一步，是因为如果该 amount 不可达就不用考虑了，要是考虑 +1 的话就会使得 int 越界，这是一个很重要的点
（4）两层 for 循环中，同样要保证 dp[i][j - coins[i]] 的有效性，但是 dp数组的更新 不能放 if 里面，不然 if 不执行就没更新了
（5）初始化的时候，amount == 0 全部初始化为 0。取第1件物品的时候，要么是可以整除、要么是正无穷
（6）最后返回的时候，也需要判断是不是正无穷，如果是正无穷需要返回 -1
*/
class Solution {
    public int coinChange(int[] coins, int amount) {
       int[][] dp = new int[coins.length][amount + 1];
       for (int j = 1; j <= amount; j++) {
           dp[0][j] = Integer.MAX_VALUE;
           if (j - coins[0] >= 0 && dp[0][j - coins[0]] != Integer.MAX_VALUE) {
               dp[0][j] = dp[0][j - coins[0]] + 1;
           }
       }
       for (int i = 1; i < coins.length; i++) {
           for (int j = 1; j <= amount; j++) {
               int temp = Integer.MAX_VALUE;
               if (j - coins[i] >= 0 && dp[i][j - coins[i]] != Integer.MAX_VALUE) {
                   temp = dp[i][j - coins[i]] + 1;
               }
               dp[i][j] = Math.min(dp[i - 1][j], temp);
           }
       }
       return dp[coins.length - 1][amount] == Integer.MAX_VALUE ? -1 : dp[coins.length - 1][amount];
   }
}




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