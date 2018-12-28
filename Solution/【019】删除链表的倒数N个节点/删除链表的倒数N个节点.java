/*
思路肯定是一个指针先走 n 步，一个指针后走
（1）要是有涉及到对第一个结点的删除操作，一般都是再设置一个 preHead.next = head 方便
（2）当然也可以在遍历的过程中设置一个 count 统计链表的结点数目 ，如果 n == count 就直接 head = head.next
*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)
            return null;
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode fast = preHead;
        for(int i = 0 ; i < n ; i ++)
            fast = fast.next;
        ListNode slow = preHead;
        while(fast.next != null){  // 跳出循环的时候，fast.next为null  slow.next为我们要删除的结点
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return preHead.next;
    }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null )  return null;
        if(head.next == null) return null;
        
        ListNode pointer1 = head;
        ListNode pointer2 = head;
        ListNode pre = head;
        int count = 0;     //利用count 计数，表示链表中的节点数
        int num = n;       // 后面要更改 n ，此处用 num记住最开始的n
        while( n > 0){
            pointer1 = pointer1.next;
            n = n - 1;
            count = count + 1;
        }
        while(pointer1 != null){
            pointer1 = pointer1.next;
            pre = pointer2;
            pointer2 = pointer2.next;
            count = count + 1;
        }
        pre.next = pointer2.next;
        if(count == num){     //如果最开始输入的 n 等于结点数目，那么要将最开始的结点删除（也就是head指向的结点）
            head = head.next;
        }
        return head;
    }
}
