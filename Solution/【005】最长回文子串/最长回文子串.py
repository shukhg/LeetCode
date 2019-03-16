'''
（1）[[0 for i in range(len(s))]for i in range(len(s))]   二维dp 数组的生成方法
（2）注意是 if ...  elif   而不是 eles if
（3）内层循环是 for j in range(i + 1)
（4）python 中是 substring()方法就是直接截取    s[start : end + 1]
'''



class Solution:
    def longestPalindrome(self, s: str) -> str:
        if s is None or len(s) == 0:
            return ""
        dp = [[0 for i in range(len(s))]for i in range(len(s))]
        start , end = 0 , 0
        for i in range(len(s)):
            for j in range(i + 1):
                if s[i] == s[j]:
                    if i <= j:
                        dp[i][j] = 1
                    elif i - j == 1:
                        dp[i][j] = 1
                    else:
                        dp[i][j] = dp[i - 1][j + 1]
                    if dp[i][j] == 1 and i - j > end - start:
                        start = j
                        end = i
        return s[start : end + 1]