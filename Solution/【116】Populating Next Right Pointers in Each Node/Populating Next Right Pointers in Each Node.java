/*
* (1) 第一种方法是利用层次遍历，注意设置两个指针，一个在另一个前面。再注意下实现细节，左边的指针要设置为null 还要判断
        遍历的过程中还要更新
 （2）利用递归，要想清楚逻辑 
*/


public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null)
            return ;
        leverOrder(root);
    }   
    public void leverOrder(TreeLinkNode root ){
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        root.next = null;
        int curCount = 1;
        int nextCount = 0;
        TreeLinkNode temp = null ;    // temp 是在 pointer 左边 
        TreeLinkNode pointer = null ;
        while( queue.isEmpty() == false ){
            pointer  = queue.poll();
            curCount --;
            if(temp != null ){
                temp.next = pointer;
                temp = pointer;      // 注意此处要更新 temp
            }
            else{   // temp 为null  新的一层的第一个结点  
                temp = pointer;
            }
            if(pointer.left != null ){
                queue.offer(pointer.left);
                nextCount ++ ;
            }
            if( pointer.right != null){
                queue.offer(pointer.right );
                nextCount ++ ;
            }
            if( curCount == 0){
                curCount = nextCount ;
                nextCount = 0;
                pointer.next = null;
                temp  = null;   // 当前层结束,重置 temp 和 pointer
                pointer = null ;
            }
        }    
    }
}

public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null )
            return ;
        if(root.left != null ){    // 左子树不为null，直接指向右子树 
            root.left.next = root.right; 
        }    
        if(root.right != null ){   // 右子树不为null
            if(root.next != null){    // 如果有next
                root.right.next = root.next.left;   // 指向 next的左子树
            }
            else{              // 否则，指向null
                root.right.next = null;
            }
        }
        connect(root.left);      // 递归左子树
        connect(root.right);     // 递归右子树  
    }
}