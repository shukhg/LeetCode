'''
首先，判断单个链表为空的时候，一个链表为空可以直接返回另一个
然后，生成 result结果链表，以及一个 temp结点用于操作
注意是一趟循环使得在result链的后面生成一个新的结点
如何传递当前的进位到下一个结点呢？   temp.next = ListNode(temp.val // 10);    利用此步骤实现进位
两个链表都为空了，还要使得不以 0 为开头
'''


class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        if l1 is None:
            return l2
        if l2 is None:
            return l1
        result = ListNode(0)
        temp = result
        while l1 or l2:
            if l1:
                temp.val = temp.val + l1.val
                l1 = l1.next
            if l2:
                temp.val = temp.val + l2.val
                l2 = l2.next
            temp.next = ListNode(temp.val // 10)
            temp.val = temp.val % 10
            if (l1 is None) and (l2 is None):
                if(temp.next.val == 0):
                    temp.next = None
                break
            temp = temp.next
        return result