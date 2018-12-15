/*
* 动态规划，状态转移方程  dp[i] = dp[i]+dp[j]*dp[i-j-1] 
* 注意索引越界的问题，处理好细节 
*/

class Solution {
    public int numTrees(int n) {
        if( n == 0 )
            return 1;
        if( n == 1)
            return 1;
        if( n == 2)
            return 2;
        int[] dp = new int[n + 1];   // 注意一次多了一个，因为 0 和 n 都符合题意
        dp[0] = 1;   //  null 也算一种 
        dp[1] = 1;
        for(int i = 2 ; i <= n ; i ++ ){
            for( int j = 0 ; j < i ; j ++ ){
                dp[i] = dp[i] + dp[j] * dp[ i - j - 1 ];  //状态转移方程
            }
        }
        return dp[ n ] ;
    }
}