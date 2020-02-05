/*
（1）可用 0 和 1 的个数可以看成不同容量的背包(二维)， dp[i][j] 中 i 表示可用0的个数，j 表示可用的 1 的个数
（2）遍历全部的 01串，对应每一个 01串，做的事情：对于可以放得下的背包分为：不放，则查看原旧背包容量；放，则 1（当前01串）+ 变小后的背包容量 
（3）状态转移方程： dp[i][j] = max(dp[i][j], 1 + dp[i - item_count0][j - item_count1])
（4）之前一直在考虑怎么考虑每个 01串 放不放的问题，实际上因为有 dp[i][j] = max(dp[i][j], ##) 这一部分的存在，就决定了每个 01串 要不要放
*/



class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        if(strs.length == 0)
            return 0;
        //[0:i-1]的字符串物品中，j个0，k个1最多能够构成字符串数量。字符串为物品，0,1数量为背包限制。
        //dp[i][j]=max(dp[i][j],dp[i-0数量][j-1数量]+1)
        int[][] dp = new int[m + 1][n + 1];
        for(String str: strs){
            int zeros = 0, ones = 0;
            //统计该字符串的0,1数量
            for(int i = 0; i < str.length(); i++){
                char c = str.charAt(i);
                if(c == '0')
                    zeros ++;
                else 
                    ones ++; 
            }
            for(int j = m; j >= zeros; j--)
                for(int k = n; k >= ones; k--)
                    dp[j][k] = Math.max(dp[j][k], 1 + dp[j - zeros][k - ones]);
        }
        return dp[m][n];
    }
}