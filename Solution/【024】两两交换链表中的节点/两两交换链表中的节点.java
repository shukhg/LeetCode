/*
链表的题，最好首先都新建一个新的头结点 newHead
遍历的时候，设置一个 p 和 pre ，p 是工作指针，pre记录一对节点中靠前的
last 记录新链表的最后一个节点，利用尾插法
*/


class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode p = head;            // 工作指针  
        ListNode q = p.next.next;    //   防止断开链接
        ListNode pre ;                // pre 记录的是相邻两个链表中靠前的
        ListNode newHead = new ListNode(0);
        ListNode last = newHead;          // 新组成链表的最后，尾插法
        while(p != null && p.next != null ){
            q = p.next.next;
            pre = p;      // 两个结点中靠前的
            last.next = p.next;
            p.next.next = pre;      // 注意此处别写为 p.next = pre
            last = pre;
            p = q;
        }
        last.next = p;          // 跳出循环的时候，p 为最后一个元素或者为 null 
        return newHead.next;
    }
}