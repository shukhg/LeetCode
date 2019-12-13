/*
（1）利用动态规划，状态转移方程是   dp[i][j] = Math.min( dp[i - 1 ][j - 1]  , Math.min( dp[ i - 1][j] , dp[i][j - 1])) + 1;
（2）要想清楚，此处 dp数组的大小是 length + 1 。因为截取单词的部分长度为0 可以，长度为 length 也可以，所以数组长度是 length+1
*/


class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        if(len1 == 0)   return len2;
        if(len2 == 0)   return len1;
        int[][] dp = new int[len1 + 1][len2 + 1];  // 0 到 i 所以是 len + 1
        for(int i = 0; i <= len1; i ++){
            dp[i][0] = i;
        }
        for(int j = 0; j <= len2; j ++){
            dp[0][j] = j;
        }
        for(int i = 1; i <= len1; i ++){
            for(int j = 1; j <= len2; j ++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else{
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}