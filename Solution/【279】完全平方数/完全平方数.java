/*
（1）看到 "最" 就应该去考虑使用动态规划
（2）求 dp[i] 的时候，内层循环变量 j 表示平方数，所以内层循环是 j * j <= i，如果 j * j > i 的话，那就没意义了，也就不需要遍历
（3）状态转移方程为  dp[i] = Math.min(dp[i], dp[i - j * j] + 1) 
*/



class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];  // 因为要考虑 0 - n
        for(int i = 1; i <= n; i ++){
            dp[i] = i;
            for(int j = 1; j * j <= i; j ++){
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}