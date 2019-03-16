'''
（1） len(s) 表示字符串的长度
（2） for i in range(len(s)) 用于迭代，要记住这个
（3） s[i] in dict    相当于直接用数组索引 表示 java中的 charAt()  并且 in dict 是只判断 dict 中 key

'''



class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if s is None:
            return 0
        if len(s) == 0:
            return 0
        dict = {}
        result = 0
        index = 0
        for i in range(len(s)):
            if s[i] in dict:
                index = max(index , dict[s[i]] + 1)
            dict[s[i]] = i
            result = max(result , i - index + 1)
        return result