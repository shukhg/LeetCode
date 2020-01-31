/*
将奇节点放在一个链表里，偶链表放在另一个链表里。然后把偶链表接在奇链表的尾部
*/



class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode o = head;   // head 为奇链表头结点，o 为奇链表尾节点
        ListNode p = head.next;     // p 为偶链表头结点
        ListNode e = p;    // e 为偶链表尾节点
        while (o.next != null && e.next != null) {   // 一次要走两步
            o.next = e.next;
            o = o.next;
            e.next = o.next;
            e = e.next;
        }
        o.next = p;
        return head;
    }
}