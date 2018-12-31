/*
设 head 到环的入口为 l1，环的入口到相遇点为 l2 ，相遇点到环的入口为 l3 
如果相遇，fast 每次走两步，则有 l1 + l2 + l3 + l2   
slow每次走一步，则有 l1 + l2 
而有 fast = 2 * slow
即 l1 + l2 + l3 + l2 = 2 * (l1 + l2)
可得 l1 = l3
也就是说，两个指针相遇后，一个从head开始走，一个从相遇点开始走，再次相遇的点即为环的入口
*/



public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null)
            return null;
        if(head.next == head)
            return head;
        ListNode fast = head;
        ListNode slow = head;
        while( fast != null && fast.next != null){  // slow fast在环内相遇
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast)
                break;
        }
        if(fast == null || fast.next == null)  // 如果没有相遇就返回null
            return null;
        
        slow = head;          // slow 回到开头
        while(slow != fast){     // slow 与 fast相遇的点即为环的入口
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}