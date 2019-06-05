'''
（1） len(s) 表示字符串的长度
（2） for i in range(len(s)) 用于迭代，要记住这个
（3） s[i] in dict    相当于直接用数组索引 表示 java中的 charAt()  并且 in dict 是只判断 dict 中 key

'''



class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if (s is None) or (len(s) == 0):
            return 0
        result , index = 1 , 0
        map = {}
        for i in range(len(s)):
            if s[i] in map:
                index = max(index, map[s[i]] + 1)
            map[s[i]] = i
            result = max(result, i - index + 1)
        return result