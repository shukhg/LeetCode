'''
此题思路很简单，就可以按照之前的反转数字的方法
如果反转得到的数字相同，那么就算对称的
'''


class Solution:
    def isPalindrome(self, x: int) -> bool:
        if x < 0 :
            return False
        if x < 10:
            return True
        reverseNum , n = 0 , x
        while n != 0:
            reverseNum = reverseNum * 10 + n % 10
            n = n // 10
        if reverseNum == x:
            return True
        else:
            return False