/*
（1）定义一个二维数组 dp 用来存储最长公共子序列的长度，其中 dp[i][j] 表示 S1 的前 i 个字符与 S2 的前 j 个字符最长公共子序列的长度。
（2）考虑 S1i 与 S2j 值是否相等，分为两种情况
（3）当 S1i == S2j 时，那么就能在 S1 的前 i-1 个字符与 S2 的前 j-1 个字符最长公共子序列的基础上再加上 S1i 这个值，最长公共子序列长度加 1
    即 dp[i][j] = dp[i-1][j-1] + 1。
（4）当 S1i != S2j 时，此时最长公共子序列为 S1 的前 i-1 个字符和 S2 的前 j 个字符最长公共子序列，
    或者 S1 的前 i 个字符和 S2 的前 j-1 个字符最长公共子序列，取它们的最大者，即 dp[i][j] = max{ dp[i-1][j], dp[i][j-1] }
*/




class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int[][] dp = new int[s1.length + 1][s2.length + 1];
        
        for(int i = 1 ; i < s1.length + 1 ; i ++){
            for(int j = 1 ; j < s2.length + 1 ; j ++){
                if(s1[i - 1] == s2[j - 1]){    //如果末端相同
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else{    //如果末端不同
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[s1.length][s2.length];
    }
}