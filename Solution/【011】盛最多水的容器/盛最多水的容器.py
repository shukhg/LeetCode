'''
对比于求两个数相加的最大和，此处也是一样的原理。
设置左右两个指针，两个指针分别往中间移动。
但是要理解的是，为什么是数组值小的那个指针往中间移动
因为如果是大的移动，就算是往中间移动的过程中遇到的数再大，也是由更小的元素限定了能装多少水。
'''

class Solution:
    def maxArea(self, height: List[int]) -> int:
        if height is None or len(height) == 0:
            return 0
        left , right = 0 , len(height) - 1
        result = 0
        while left < right:
            temp = min(height[left] , height[right]) * (right - left)
            if temp > result:
                result = temp
            if height[left] < height[right]:
                left  += 1
            else:
                right -= 1
        return result