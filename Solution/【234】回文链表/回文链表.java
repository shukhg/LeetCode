/*
（1）常规的方法可以找到中点，然后利用栈进行比较
（2）其实可以利用快慢指针找到中点，一边遍历一遍反转，使得 fast 指针跳出循环的时候 前半部分 已经翻转完成
（3）然后再比较翻转后的前半部门 与 后半部分 即可
*/



class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        ListNode pre = head, prepre = null;
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            pre.next = prepre;
            prepre = pre;
        }
        if(fast != null) {
            slow = slow.next;
        }
        while(pre != null && slow != null) {
            if(pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }
        return true;
    }
}