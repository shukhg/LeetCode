/*
简单的头插入法，但是注意一个关键的细节，head.next 要置为 null ，不然会无限循环。
注意此处在构建链表的时候是没有问题的，问题是出在第一个和第二个结点中间会有双向链表，所以判断结果遍历的时候就会有问题
*/


public class Solution {
    public ListNode ReverseList(ListNode head) {
        if(head == null)
            return null;
        if(head.next == null)
            return head;
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode pointer = head.next;
        head.next = null;          // 此处保证不会无限循环
        while( pointer != null){
            ListNode temp = pointer.next;    // 为了防止断链
            pointer.next = preHead.next;
            preHead.next = pointer;
            pointer = temp;
        }
        return preHead.next;
    }
}