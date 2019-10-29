/*
*利用动态规划算法， dp[i][j]代表下标为 i 和 j 的元素间是否构成回文子串
*中间过程找最大值或者记录最大长度和初始点即可
*/


class Solution {
    public:
        string longestPalindrome(string s) {
            if(s.empty() || s.length() == 0){
                return "";
            }
            int len = s.length();
            vector<vector<int>> dp(len,vector<int>(len));
            dp[0][0] = 1;
            int max_sub_len = 0;
            int start = 0;
            int end = 0;
            for(int i = 0 ; i < len ; i ++){
                for(int j = 0 ; j <= i ; j ++){
    
                    if(s.at(i) == s.at(j)){
                        if(i == j){
                            dp[j][i] = 1;
                        }
                        else if(i == j + 1){
                            dp[j][i] = 1;
                        }
                        else{
                            dp[j][i] = dp[j + 1][i - 1];
                        }
                        if(dp[j][i] == 1 && (i - j + 1) > max_sub_len){
                            start = j;
                            end = i;
                            max_sub_len = i - j + 1;
                        }
                    }
                }
            }
            return s.substr(start, max_sub_len);
        }
    };