/*
*利用动态规划算法， dp[i][j]代表下标为 i 和 j 的元素间是否构成回文子串
*中间过程找最大值或者记录最大长度和初始点即可
*/


class Solution {
    public static String longestPalindrome(String s) {
        if(s == null || s.length() == 0)  return "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        int start = 0;       //记录起始点
        int length = 0;      //记录最大长度
        for(int i = 0 ; i < s.length() ; i++){
        	for(int j = 0 ; j <= i ; j ++){     
        		if(s.charAt(i) == s.charAt(j)){
        			if(i - j == 1){
        				dp[i][j] = true;
        			}
        			else if(i - j == 0)  dp[i][j] = true;
        			else{
        				dp[i][j] = dp[i - 1][j + 1] && true ;
        			}
        		}
        		if(length < i - j + 1 && dp[i][j]){  // 发现了更长的回文子串
    				length = i - j + 1;
    				start = j ;
    			}
        		
        	}
        }
        String result = s.substring(start, start + length );
        return result;
    }
}