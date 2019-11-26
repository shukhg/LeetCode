'''
（1）[[0 for i in range(len(s))]for i in range(len(s))]   二维dp 数组的生成方法
（2）注意是 if ...  elif   而不是 eles if
（3）内层循环是 for j in range(i + 1)
（4）python 中是 substring()方法就是直接截取    s[start : end + 1]
'''



class Solution:
    def longestPalindrome(self, s: str) -> str:
        if s is None or len(s) == 0:
            return s
        len_s = len(s)
        dp = [[False for i in range(len_s)] for j in range(len_s)]
        start, max_sub_len = 0, 0
        for i in range(len_s):
            for j in range(i, -1, -1):
                if s[i] == s[j]:
                    if i - j <= 1:
                        dp[j][i] = True
                    else:
                        dp[j][i] = dp[j + 1][i - 1]
                    if dp[j][i] and i - j + 1 > max_sub_len:
                        max_sub_len = i - j + 1
                        start = j
        return s[start: start + max_sub_len]
