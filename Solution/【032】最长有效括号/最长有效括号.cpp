/*
我们用 dp[i] 表示以 i 结尾的最长有效括号；
1、当 s[i] 为 (,dp[i] 必然等于 0，因为不可能组成有效的括号；

2、那么 s[i] 为 )
2.1 当 s[i-1] 为 (，那么 dp[i] = dp[i-2] + 2；
2.2 当 s[i-1] 为 ) 并且 s[i-dp[i-1] - 1] 为 (，那么 dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]；     这里是关键

*/


// 解法1：动态规划

class Solution {
public:
    int longestValidParentheses(string s) {
        if(s.empty() || s.size() == 0){
            return 0;
        }
        vector<int> dp(s.size(), 0);
        int result = 0;
        for(int i = 0 ; i < s.size(); i ++){
            if(i > 0 && s.at(i) == ')'){
                if(s.at(i - 1) == '('){
                    if(i > 2)   dp[i] = dp[i - 2] + 2;
                    else    dp[i] = 2;
                }
                else{           // 关键是要理解下面的   i - dp[i - 1] - 1 
                    if(i - dp[i - 1] - 1 >= 0 && s.at(i - dp[i - 1] - 1) == '('){
                        if (i - dp[i - 1] - 2 >= 0)  dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2];
                        else    dp[i] = dp[i - 1] + 2;
                    }
                }
            }
            result = max(result, dp[i]);
        }
        return result;
    }
};



// 解法2：利用栈，找到所有可以匹配的索引号，然后找出最长连续数列，然后把连续数列进行排序。
//       但是为了节约时间复杂度，可以省略排序的过程，在弹栈的时候进行操作
class Solution {
public:
    int longestValidParentheses(string s) {
        if(s.empty() || s.size() == 0){
            return 0;
        }
        stack<int> my_stack;
        my_stack.push(-1);      // 一定要先放一个 -1 进去
        int result = 0;
        for(int i = 0; i < s.size(); i ++){
            if(s.at(i) == '('){
                my_stack.push(i);
            }
            else{
                my_stack.pop();
                if(my_stack.empty()){     // 这里判断为 空 是关键
                    my_stack.push(i);
                }
                else{
                    result = max(result, i - my_stack.top());  // 关键是理解这里
                }
            }
        }
        return result;
    }
};




