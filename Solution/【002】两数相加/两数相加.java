/*
*一步步新建结点，最后注意两个链表都走到尾的时候要判断有没有进位
*/



class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {    //全为null就直接返回
            return null;
        }
        if (l1 == null) {    //因为上面判断了l1和l2，所以这里如果l1是null，那么l2必然有值，直接返回l2即可
            return l2;
        }
        if (l2 == null) {   //  同上 
            return l1;
        }
        ListNode result = new ListNode(0);  // 新的头结点
        ListNode temp = result;
        while (true) {
            if (l1 != null) {
                temp.val = temp.val + l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                temp.val = temp.val + l2.val;
                l2 = l2.next;
            }
            temp.next = new ListNode(temp.val / 10);  // 有进位则进位 
            temp.val = temp.val % 10;      // 取余数表示本结点可能进位后的值 
            if (l1 == null && l2 == null) {
                if(temp.next.val == 0)
                    temp.next = null; 
                break;
            }
            temp = temp.next;
        }
        return result;
    }
}