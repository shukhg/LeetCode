/*
（1）状态   dp[i][j] 表示 s 的第 i 个字符到第 j 个字符组成的子串中，最长的回文序列长度是多少
（2）转移方程
    如果 s 的第 i 个字符和第 j 个字符相同的话，dp[i][j] = dp[i + 1][j - 1] + 2
    如果 s 的第 i 个字符和第 j 个字符不同的话，则最长子序列是 丢掉最左边一个元素后的最长子序列长度 和 丢掉最右边一个元素后的最长子序列长度 中最大的一个， 
        故有 dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])
（3）初始化 dp[i][i] = 1 单个字符的最长回文序列是 1
（4）结果   dp[0][len - 1]
*/



class Solution {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } 
                else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }
}