/*
（1）因为空字符串也需要去匹配，所以长度为 0 的时候也要考虑，长度也就需要 +1
（2）p 串长度为 0 的时候除了 dp[0][0] 都为 false，s 串长度为 0 的时候要看 p 串是否为 *
（3）p.charAt(j - 1) == '*' 的时候，要想明白  dp[i][j] = dp[i - 1][j] || dp[i][j - 1];   前面为啥是 dp[i - 1][j]  而不是 dp[i - 1][j - 1] 
*/





class Solution {
    public boolean isMatch(String s, String p) {
        int len_s = s.length(), len_p = p.length();
        boolean[][] dp = new boolean[len_s + 1][len_p + 1];  
        dp[0][0] = true;
        for(int j = 1; j < len_p + 1; j ++){
            if(p.charAt(j - 1) == '*'){
                dp[0][j] = dp[0][j - 1];
            }
        }
        for(int i = 1; i < len_s + 1; i ++){
            for(int j = 1; j < len_p + 1; j ++){
                if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?'){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else if(p.charAt(j - 1) == '*'){
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];   // 这里是关键
                }
            }
        }
        return dp[len_s][len_p];
    }
}