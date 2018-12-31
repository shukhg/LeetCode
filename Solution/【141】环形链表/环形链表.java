/*
一个指针一次走两步，一个指针一次走一步即可
*/

public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode fast = head ;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow )  
                return true;
        }
        return false;
    }
}