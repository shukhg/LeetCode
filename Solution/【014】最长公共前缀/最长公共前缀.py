'''
每次都是用两个字符串中最短的字符串去匹配另一个字符串
最后返回的是更新后的最短的字符串
'''



class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        if strs is None or len(strs) == 0 or strs[0] is None:
            return ""
        short_str = strs[0]
        for i in range(1 , len(strs)):
            long_str = strs[i]
            if long_str is None or len(long_str) == 0:
                return ""
            if len(long_str) < len(short_str):
                temp = long_str
                long_str = short_str
                short_str = temp
            while long_str.find(short_str) != 0:    # 这里一定要用 find() 而不是用 index()
                short_str = short_str[0 : -1]
        return short_str