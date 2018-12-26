/*
利用层次遍历，中间处理好 temp 是否为 null 以及 temp更新的问题
*/





public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null )
            return ;
        levelOrder( root );
        
    }
    public void levelOrder(TreeLinkNode root ){
        Queue<TreeLinkNode> queue = new LinkedList<>();
        TreeLinkNode pointer = root;
        root.next = null;
        TreeLinkNode temp = null;    // temp 在 pointer 左边
        int curCount = 1;
        int nextCount = 0;
        queue.offer(root);
        while(queue.isEmpty() == false){
            pointer = queue.poll();
            curCount --;
            if(temp == null){
                temp = pointer;
            }
            else{              //  temp 不为 null  说明pointer不是当前层第一个结点
                temp.next = pointer;      
                temp = pointer ;        // 更新temp
            }
            if(pointer.left != null ){
                queue.offer(pointer.left);
                nextCount ++;
            }
            if(pointer.right != null){
                queue.offer(pointer.right);
                nextCount ++;
            }
            if(curCount == 0){
                curCount = nextCount ;
                nextCount = 0;
                pointer.next = null;         //  新的一层，最右边结点的 next 为 null
                temp = null ;             // 新的一层，要更新 temp
            }
        }
    }
}