'''
判断两个相邻的字符对应的阿拉伯数字，如果左边小于右边，就减去左边。如果左边大于右边，就加上左边
从前往后或者从后往前处理都是可以的，从前往后就要处理最后一个字符
'''


class Solution:
    def romanToInt(self, s: str) -> int:
        if s is None or len(s) == 0:
            return 0
        result = 0
        count = 1
        dict = {'I':1 , 'V':5 , 'X':10 , 'L':50 , 'C':100 , 'D':500 , 'M':1000}
        for i in range(1 , len(s)):
            num1 = dict[s[i - 1]]
            num2 = dict[s[i]]
            if num1 < num2:
                result = result - num1
            else:
                result = result + num1
        result = result + dict[s[len(s) - 1]]
        return result