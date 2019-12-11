/*
找到倒数第 k 个结点即可
*/





class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(k < 0 || head == null || head.next == null)  return head;
        ListNode new_head = new ListNode(0);
        new_head.next = head;
        ListNode fast = new_head;
        ListNode slow = new_head;
        int len = 0;
        while(fast.next != null){
            len ++;
            fast = fast.next;
        }
        k = k % len;
        if(k == 0)  return head;
        fast = new_head;
        while(k != 0){
            fast = fast.next;
            k --;
        }
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        ListNode q = slow.next;
        fast.next = new_head.next;
        new_head.next = q;
        slow.next = null;
        return new_head.next;
    }
}