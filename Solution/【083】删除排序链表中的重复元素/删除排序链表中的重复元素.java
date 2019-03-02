/*
简单题，慢慢想，处理好细节就可以了
*/


class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode pointer = head;
        ListNode pre = head;
        while(pointer != null){
            if(pre.val != pointer.val){
                pre.next = pointer;
                pre = pointer;
            }
            pointer = pointer.next;
        }
        pre.next = null;
        return head;
    }
}