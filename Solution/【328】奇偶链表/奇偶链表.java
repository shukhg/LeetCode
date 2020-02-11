/*
（1）将奇节点放在一个链表里，偶链表放在另一个链表里。然后把偶链表接在奇链表的尾部
（2）直接用 o 和 e 在循环中去迭代，最后 return head 就可以了
（3）while 循环中是 o.next != null && e.next != null
（4）直接 o.next = e.next; o = o.next; e.next = o.next; e = e.next; 
（5）最后 o.next = p，此处的 p 是一开始初始化的 head.next，不随着循环进行遍历
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