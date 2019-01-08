/*
简单 dp
*/

class Solution {
    public int fib(int N) {
        if(N == 0)
            return 0;
        if(N == 1)
            return 1;
        if(N == 2)
            return 1;
        int[] dp = new int[N];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2 ; i < N ; i ++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N - 1];
    }
}