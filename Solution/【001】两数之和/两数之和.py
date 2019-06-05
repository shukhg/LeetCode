'''
enumerate() 函数用于将一个可遍历的数据对象(如列表、元组或字符串)组合为一个索引序列，同时列出数据和数据下标，一般用在 for 循环当中。
enumerate(sequence, [start=0])
sequence – 一个序列、迭代器或其他支持迭代对象。
'''

'''
常规方法很容易想到，用内外两层循环即可
更加有效的方法是利用 HashMap存储 nums数组的值-索引 
'''



class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        dict = {}
        result = []
        for i , num in enumerate(nums):
            div = target - num
            if (div) in dict:
                result.append(i)
                result.append(dict[div])
                return result
            dict[num] = i
        return result