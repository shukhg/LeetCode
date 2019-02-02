/*
看到链表的题目，都最好用一个新的头结点 newHead
注意最后的时候可能有一个链表没有插入完
*/


// 非递归
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        ListNode newHead = new ListNode(0);
        ListNode p1 = l1; 
        ListNode p2 = l2;
        ListNode q = newHead;   // 新链表的最后
        while(p1 != null && p2 != null){
            if(p1.val < p2.val){
                q.next = p1;
                q = p1;
                p1 = p1.next;
            }
            else{
                q.next = p2;
                q = p2;
                p2 = p2.next;
            }
        }
        if(p1 == null){
            while(p2 != null){
                q.next = p2;
                q = p2;
                p2 = p2.next;
            }
        }
        else{
            while(p1 != null){
                q.next = p1;
                q = p1;
                p1 = p1.next;
            }
        }
        return newHead.next;
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