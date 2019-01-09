/*
（1）链表的题目，最好都新建一个头结点 preHead
（2）注意一定要有防止断链的指针
（3）内层循环是  pre.next != null && pre.next.val < cur.val 
    就保证了 pre 后面的一个位置就是插入位置 
    就算是 pre.next == null 也是符合要求的 
*/


class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode preHead = new ListNode(0);   // 注意 preHead 没有指向 head 
        ListNode pre = preHead;        
        ListNode cur = head;    // 从第 1 个元素开始
        while(cur != null){
            pre = preHead;
            while(pre.next != null && pre.next.val < cur.val){
                pre = pre.next;
            }
            ListNode curNext = cur.next;   //   curNext 存放 cur 的下一个结点防止断链
            cur.next = pre.next;
            pre.next = cur;
            cur = curNext;  // 更新 cur，使其后移
        }
        return preHead.next;
    }
}