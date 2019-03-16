/*
*利用动态规划算法， dp[i][j]代表下标为 i 和 j 的元素间是否构成回文子串
*中间过程找最大值或者记录最大长度和初始点即可
*/


class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0)
            return "";
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        int start = 0 , end = 0;
        for(int i = 0 ; i < length ; i ++){       // 这里两层循环一定要都是从 0 开始的，比如"bb"这样的情况，不能从 1 开始
            for(int j = 0 ; j <= i ; j ++){       // 不一定每个动态规划都有行和列的初始化
                if(s.charAt(i) == s.charAt(j)){
                    if(i <= j){
                        dp[i][j] = true;
                    }
                    else if(i - j == 1){
                        dp[i][j] = true;
                    }
                    else{
                        dp[i][j] = dp[i - 1][j + 1];
                    }
                    if(dp[i][j] && i - j  > end - start ){
                        start = j;
                        end = i;
                    }
                }
            }
        }
        return s.substring(start , end + 1);
    }
}