'''
1. 首先需要丢弃字符串前面的空格；
2. 然后可能有正负号（注意只取一个，如果有多个正负号，那么说这个字符串是无法转换的，返回0 	比如测试用例里就有个“+-2”）；
3. 字符串可以包含0~9以外的字符，如果遇到非数字字符，那么只取该字符之前的部分，如“-00123a66”返回为“-123”；
4. 如果超出int的范围，返回边界值（2147483647或-2147483648）。
5. 注意字符转化为整数的方法， digit = ord(str[i]) - ord('0')   此点一定要记住 
'''


class Solution:
    def myAtoi(self, str: str) -> int:
        str = str.strip()    # 记住要先去除空格
        if str is None or len(str) == 0:
            return 0
        result , i = 0 , 0
        flag = True
        if str[0] == '-':
            flag = False
            i = i + 1     # 没有 i ++的写法
        elif str[0] == '+':
            i = i + 1
        while i < len(str):
            digit = ord(str[i]) - ord('0')  # 字符转数字
            if digit < 0 or digit > 9:
                return result
            if flag:
                result = result * 10 + digit
                if result > 2**31 - 1:
                    return 2**31 - 1
            else:
                result = result * 10 - digit
                if result < -2**31:
                    return -2**31
            i = i + 1
        return result