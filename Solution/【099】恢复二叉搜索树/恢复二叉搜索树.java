/*
递归中序遍历二叉树，设置一个pre指针，记录当前节点中序遍历时的前节点，如果当前节点大于pre节点的值，说明需要调整次序。
有一个技巧是如果遍历整个序列过程中只出现了一次次序错误，说明就是这两个相邻节点需要被交换。如果出现了两次次序错误，那就需要交换这两个节点。
*/

class Solution {
    
    TreeNode mistake1 , mistake2;    //全局变量存储2个错误的结点 
    TreeNode pre ;
    public void recoverTree(TreeNode root) {
        search( root);
        int temp = mistake1.val;
        mistake1.val = mistake2.val;
        mistake2.val = temp;
    }
    
    public void search(TreeNode root ){
        if( root == null )
            return ;
        search(root.left);
        if( pre != null && pre.val >= root.val){   // 和 098 的 pre 一样，这里也是 pre != null 然后判断 pre.val 和 root.val
            if(mistake1 == null){     // 初始状态
                mistake1 = pre;
                mistake2 = root;
            }
            else{    // 已经找到了 mistake1 现在去找出现另一次错误的 mistake2
                mistake2 = root;   
            }
        }
        pre = root ;    // 更新 pre
        search(root.right);
    }
}

