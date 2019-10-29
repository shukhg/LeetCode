




class Solution {
public:
    string longestPalindrome(string s) {
        if(s.empty() || s.length() == 0){
            return "";
        }
        int len = s.length();
        vector<vector<int>>  dp(len,vector<int>(len));//定义二维动态数组
        dp[0][0] = 1;
        int max_sub_len = 0;
        int start = 0;
        int end = 0;
        for(int i = 0 ; i < len ; i ++){
            for(int j = 0 ; j <= i ; j ++){
                if(s.at(i) == s.at(j)){
                    if(i == j){
                        dp[j][i] == 1;
                    }
                    else if(i = j + 1){
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