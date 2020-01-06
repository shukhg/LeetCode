/*
（1）找到中点
（2）逆转后半部分
（3）拼接前半部分和逆转过的后半部分
*/




class Solution {
    public void reorderList(ListNode head) {
        if(head == null) return;
        ListNode slow = head, fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode right = reverse(slow.next);
        slow.next = null;
        
        ListNode left = head;
        while(right != null){
            ListNode next = right.next;
            right.next = left.next;
            left.next = right;
            right = next;
            left = left.next.next;
        }
    }
    
    public ListNode reverse(ListNode head){
        ListNode prev = null;
        while(head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}