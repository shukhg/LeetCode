/*
用 dp[i][j] 表示 s1 的前 i 元素和 s2 前 j 元素是否交错组成 s3 前 i+j 元素
dp[i][j] = (dp[i-1][j] && s3[i+j-1] == s1[i-1]) || (dp[i][j-1] && s2[j-1] == s3[i+j-1])
*/




class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len_1 = s1.length();
        int len_2 = s2.length();
        int len_3 = s3.length();
        if(len_1 + len_2 != len_3)  return false;
        boolean[][] dp = new boolean[len_1 + 1][len_2 + 1];
        dp[0][0] = true;
        for(int j = 1; j <= len_2; j ++){
            dp[0][j] = dp[0][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
        }
        for(int i = 1; i <= len_1; i ++){
            dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        for(int i = 1; i <= len_1; i ++){
            for(int j = 1; j <= len_2; j ++){
                dp[i][j] = (dp[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(i + j - 1)) 
                || (dp[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(i + j - 1))));
            }
        }
        return dp[len_1][len_2];
    }
}