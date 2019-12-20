/*
（1）写个函数，功能是找到当前 root 右边的下一层中最靠左的结点。因为是要在右边找，所以一定要先构建好右子树，也就是先递归右子树然后再递归左子树
（2）如果root有左节点和右节点，则左节点的Next为右节点。
    如果root右节点为Null，则查找父节点的兄弟节点的最左边子元素
    如果root右节点不为Null，则next为父节点的兄弟节点的最左边子元素
*/





class Solution {
    public Node connect(Node root) {
        if(root == null)    return null;
        if(root.left != null){
            if(root.right != null){       // 如果root有左节点和右节点，则左节点的Next为右节点
                root.left.next = root.right;
            }
            else{        // 如果root右节点为Null，则查找父节点的兄弟节点的最左边子元素
                root.left.next = findLeftChild(root);
            }
        }
        if(root.right != null){
            root.right.next = findLeftChild(root);
        }
        connect(root.right);   // 一定要先构建右子树再构建左子树，因为需要在右子树中查找
        connect(root.left);
        return root;
    }
    public Node findLeftChild(Node root){   // 找到 root 右面的下一层的最左边的结点
        if(root.next == null)   return null;
        while(root.next != null){
            if(root.next.left != null){
                return root.next.left;
            }
            if(root.next.right != null){
                return root.next.right;
            }
            root = root.next;
        }
        return null;
    }
}