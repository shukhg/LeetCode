/*
（1）如果 s[i] = '0'    
    判断 s[i-1] = '1' or '2'，dp[i]=dp[i-2], 否则返回 0
（2）如果 s[i] 为其他
    判断 s[i-1] = '1' or '2'，分情况讨论得到 dp[i] = dp[i-1] + dp[i-2]
    如果 s[i-1] 为其他，则 d[i] = dp[i-1]

既然此处只用到 dp[i - 2]、dp[i - 1]，则可以用两个变量代替，而不用 dp 数组，这是一种常用的思想
*/



//  动态规划
class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        if(s.charAt(0) != '0')  dp[1] = 1;
        for(int i = 2; i < s.length() + 1; i ++){
            if(s.charAt(i - 1) == '0'){
                if(s.charAt(i - 2) == '1' || s.charAt(i - 2) == '2'){
                    dp[i] = dp[i - 2];
                }else{
                    return 0;
                }
            }
            else if(s.charAt(i - 2) == '1'){
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            else if( s.charAt(i - 2) == '2' && s.charAt(i - 1) >= '1' && s.charAt(i - 1) <= '6' ){
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            else{
                dp[i] = dp[i - 1];
            }
        }
        return dp[s.length()];
    }
}

