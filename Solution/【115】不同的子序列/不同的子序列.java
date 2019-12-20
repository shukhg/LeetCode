/*
（1）f[i][j]表示s1的前i个字符的子序列中，包含多少个s2的前 j 个字符子串
（2）如果s1的第 i 个字符和s2的第 j 个字符不同的话，意味着s1的第 i 个字符，必不参与组成子序列
     如果s1的第 i 个字符和s2的第 j 个字符相同的话，意味着s1的第 i 个字符，可以参与组成子序列，也可不参与组成子序列
（3）初始化的时候，注意 空字符串 为任何字符串的字串，但是这种情况下要设置为字串数目为 1 而不是无穷 或者 length + 1
*/






class Solution {
    public int numDistinct(String s, String t) {
        if(s == null || s.length() == 0 || s.length() < t.length())    return 0;
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for(int i = 0; i < s.length() + 1; i ++){  // 因为空集是所有字符串的子集，所以这一行都是 1
            dp[i][0] = 1;
        }
        for(int i = 1; i < s.length() + 1; i ++){
            for(int j = 1; j < t.length() + 1; j ++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
                else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }
}