'''
具体思考见java解法，主要是要理清楚思路
'''


class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if s is None or len(s) <= numRows or numRows == 1:
            return s
        k1 = 2 * numRows - 2
        result = ""
        for i in range(numRows):
            if i == 0 or i == numRows - 1:
                result = result + s[i]
                index = i + k1
                while index < len(s):
                    result = result + s[index]
                    index = index + k1
            else:
                result = result + s[i]   
                k2 = k1 - 2 * i        # 注意 k2 的规律
                index = i + k2
                while index < len(s):
                    result = result + s[index]
                    k2 = k1 - k2        # 注意这里更新了 k2
                    index = index + k2
        return result