/*
新建两个链表头然后分开处理就行了
*/


class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null)
            return head;
        ListNode newHead1 = new ListNode(0);
        ListNode newHead2 = new ListNode(0);
        ListNode pointer = head;
        ListNode q1 = newHead1;   // newHead后半段的尾插法
        ListNode q2 = newHead2;   // newHead前半段的尾插法
        while( pointer != null ){
            if( pointer.val < x  ){
                q1.next = pointer;
                q1 = pointer;
                
            }
            else{
                q2.next = pointer;
                q2 = pointer;
            }
            pointer = pointer.next;
        }
        q1.next = newHead2.next;
        q2.next = null;
        return newHead1.next ;
    }
}