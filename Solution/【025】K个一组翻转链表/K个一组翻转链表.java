/*
尾插法，用 tail 记录要翻转的部分的最后一个元素。
在遍历翻转部分元素的时候，每次把当前元素移到 tail 后面
关键是用 count 记录 tail 能走的步数，如果 步数 < k 则不进行翻转了
*/




class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode new_head = new ListNode(0);
        new_head.next = head;
        ListNode pre = new_head;
        ListNode tail = new_head;
        while (true) {
            int count = 0;
            while (tail != null && count != k) {   // 为了找到 tail
                count++;
                tail = tail.next;
            }
            if (tail == null)   break;    // 判断 tail 是否为 空
            ListNode head1 = pre.next;
            while (pre.next != tail) {      // 尾插法，pre 在进入循环前的初始值为 需要翻转部分 的前一个
                ListNode cur = pre.next;
                pre.next = cur.next;
                cur.next = tail.next;
                tail.next = cur;
            }
            pre = head1;     // 更新 pre 和 tail
            tail = head1;
        }
        return new_head.next;
    }
}