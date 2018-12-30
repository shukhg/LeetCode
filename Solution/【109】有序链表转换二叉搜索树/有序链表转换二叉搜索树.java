/*
 采用递归
1、利用快慢指针，找到中间点的前一个点。
2、然后将root设为中间点的值。
3、此时将中间左边的链表切出来，进行递归。方法是mid-1的链表结点后继为空。
4、左边链表从head开始
5、右边链表从中间结点的下一个开始。
*/





class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null )
            return null;
        if(head.next == null)
            return new TreeNode(head.val);
        ListNode fast = head.next.next ;  // 已经保证了head.next != 0
        ListNode slow = head;
        while( fast!= null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next ;
        }         // 得到的slow 是中间结点的前一个 
        ListNode temp = slow.next.next;
        TreeNode root = new TreeNode(slow.next.val);
        slow.next = null;    // 断开链接
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(temp);
        return root ;
    }
}