/*
关键之处是 p.val 等于 p.next.val 的时候，记录 value 然后 while 循环判断后面的是否也为 value
*/


class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode new_head = new ListNode(0);
        new_head.next = head;
        ListNode p = head;
        ListNode pre = new_head;
        while(p != null && p.next != null){
            if(p.val == p.next.val){
                int val = p.val;
                while(p != null && p.val == val){
                    p = p.next;
                }
                pre.next = p;
            }
            else{
                pre = p;
                p = p.next;
            }
        }
        return new_head.next;
    }
}