'''
字符串的切片，str[::-1]，实现字符串的反转。
其他的List 等也可以这样实现反转
注意 python3 中的数字已经没有的范围限制，但是要人为的处理 int的范围  -2^31 ~ 2^31 - 1 
'''


class Solution:
    def reverse(self, x: int) -> int:
        if x == 0:
            return 0
        str_x = str(x)
        result = ''
        if str_x[0] == '-':
            result = result + '-'
        result = result + str_x[::-1].lstrip("0").rstrip("-")   # 这里要去掉 x 左边的 - 和右边的 0
        result = int(result)
        if result < -2**31 or result > 2**31 - 1:
            return 0
        else:
            return result