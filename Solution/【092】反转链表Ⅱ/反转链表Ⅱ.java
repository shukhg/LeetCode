/*
找到 m 点，然后后面的 n-m 个点依次利用头插法即可
*/

class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        // 找到翻转链表部分的前一个节点, 1->2->3->4->5->NULL, m = 2, n = 4 指的是 节点值为1
        for (int i = 0; i < m - 1; i++) pre = pre.next;
        // 用 pre, start, tail三指针实现插入操作
        // tail 是插入pre,与pre.next的节点
        ListNode start = pre.next;
        ListNode tail = start.next;
        for (int i = 0; i < n - m; i++) {
            start.next = tail.next;
            tail.next = pre.next;
            pre.next = tail;
            tail = start.next;
        }
        return dummy.next;
    }
}