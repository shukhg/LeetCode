/*
看到链表的题目，都最好用一个新的头结点 newHead
注意最后的时候可能有一个链表没有插入完
*/


// 非递归
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode new_head = new ListNode(0);
        ListNode q = new_head;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                q.next = l1;
                l1 = l1.next;
                q = q.next;
            }
            else{
                q.next = l2;
                l2 = l2.next;
                q = q.next;
            }
        }
        if(l1 == null){
            q.next = l2;
        }
        else{
            q.next = l1;
        }
        return new_head.next;
    }
}


// 递归解法
public class Solution{
    public static ListNode mergeTwoLists(ListNode l1 , ListNode l2){
        if(l1 == null) 
            return l2;
        if(l2 == null ) 
            return l1;
        ListNode head = null;
        if(l1.val < l2.val){
            head = l1;
            head.next = mergeTwoLists(l1.next , l2);
        }
        else{
            head = l2;
            head.next = mergeTwoLists(l1 , l2.next);
        }
        return head;
    }
}