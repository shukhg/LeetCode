/*
利用动态规划算法， dp[i][j]代表下标为 i 和 j 的元素间是否构成回文子串
中间过程找最大值或者记录最大长度和初始点即可
*/


class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0){
            return "";
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int start = 0, end = 0, max = 0;
        dp[0][0] = true;
        for(int i = 0 ; i < len ; i ++){
            for(int j = 0 ; j <= i ; j ++){
                if(s.charAt(i) == s.charAt(j)){
                    if(i == j){
                        dp[j][i] = true;
                    }
                    else if(i == j + 1){
                        dp[j][i] = true;
                    }
                    else{
                        dp[j][i] = dp[j + 1][i - 1];
                    }
                    if(dp[j][i] == true && (i - j + 1) > max){
                        start = j;
                        end = i;
                        max = i - j + 1;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }
}