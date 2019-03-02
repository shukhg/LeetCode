/*
关键之处是 p != null && p.next != null
然后判断 p.val 是否等于 p.next.val
*/


class Solution {
    public ListNode deleteDuplicates(ListNode pHead) {
        if(pHead == null || pHead.next == null)
            return pHead;
        ListNode newHead = new ListNode(0);
        newHead.next = pHead;
        ListNode p = pHead;
        ListNode pre = newHead;
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
        return newHead.next;
    }
}