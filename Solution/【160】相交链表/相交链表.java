/*
（1）最直接的想法是找到两个链表的长度差，然后长链表走过长度差后再进行对比
（2）画个图理解最好了
    指针 pA 指向 A 链表，指针 pB 指向 B 链表，依次往后遍历
    如果 pA 到了末尾，则 pA = headB 继续遍历
    如果 pB 到了末尾，则 pB = headA 继续遍历
    比较长的链表指针指向较短链表head时，长度差就消除了
    如此，只需要将最短链表遍历两次即可找到同等的位置
*/



public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
