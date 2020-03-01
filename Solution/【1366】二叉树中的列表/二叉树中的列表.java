/*
（1）其实这题就是“树的子结构”，几乎一样的方法
（2）所以如果 root.val != head.val 的话，直接返回 false
（3）也就是需要写一个辅助函数，实现上述的功能
（4）head == null 说明匹配完了，返回 true
（5）root == null 直接返回 false
（6）root.val == head.val 则 树分别往左右子树走，链表则 head.next
*/


// 错误解法，因为这样会使得链表在树中的位置不是连续的
class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (null == head) return true;
        if (null == root) return false;
        if (head.val != root.val) return isSubPath(head, root.left) || isSubPath(head, root.right);  // 这里错了，会使得 树 断开
        return isSubPath(head.next, root.left) || isSubPath(head.next, root.right);
    }
}



class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        //先判断当前的节点，如果不对，再看左子树和右子树呗
        return isSub(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean isSub(ListNode head, TreeNode node) {
        //特判：链表走完了，返回true
        if (head == null) {
            return true;
        }
        //特判：链表没走完，树走完了，这肯定不行，返回false
        if (node == null) {
            return false;
        }
        //如果值不同，必定不是啊
        if (head.val != node.val) {
            return false;
        }
        //如果值相同，继续看，左边和右边有一个满足即可
        return isSub(head.next, node.left) || isSub(head.next, node.right);
    }
}