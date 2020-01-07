/*
（1）如果空间复杂度不要求是常数级，那就用递归的方法
（2）找到链表的中点，断开左边的链接，分别对左边和右边递归
（3）跳出递归的条件应该是 head == null || head.next == null
（4）拿到递归结束的两个链表（left 和 right），对这两个链表进行合并即可
*/



class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode temp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(temp);
        ListNode p = new ListNode(0);
        ListNode result = p;
        while(left != null && right != null){
            if(left.val < right.val){
                p.next = left;
                left = left.next;
            }
            else{
                p.next = right;
                right = right.next;
            }
            p = p.next;
        }
        if(left != null)    p.next = left;
        else    p.next = right;
        return result.next;
    }
}