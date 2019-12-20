/*
（1）如果左子树不为空，则将左子树的 next 指向右子树。
（2）除了考虑 root 的子树，还需要考虑遍历的时候 root 的 next，所以如果 root.next 不为空，还要考虑 root.right.next = root.next.left
（3）当前结点处理完毕，递归地遍历左右子树
*/



class Solution {
    public Node connect(Node root) {
        if(root == null)    return null;
        if(root.left != null){
            root.left.next = root.right;
        }
        if(root.next != null && root.right != null){
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
}



public class Solution {
    public Node connect(Node root) {
        if(root == null)
            return null;
        levelOrder(root);
        return root;
    }   
    public void levelOrder(Node root ){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        root.next = null;
        int curCount = 1;
        int nextCount = 0;
        Node temp = null ;    // temp 是在 pointer 左边 
        Node pointer = null ;
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

