/*
如果右子树不为空，则右子树最后肯定为左子树最有一个靠右的孩子节点的右子树，而左子树最后成为整棵树的右子树。
首先判断左子树是否为空，不为空就寻找到树根的左孩子节点，然后寻找该节点是否有右孩子，如果有继续寻找，直到找到属于叶子节点的最右边的右孩子。
此时，该节点的右子树“指向”当前树的右子树，并将当前左子树变为树根的右孩子，将整棵树左孩子置为空。
最后，根节点“指向”根节点的右孩子，继续上述操作，直到整棵树遍历完即得到结果
*/


// 递归写法，容易理解。
class Solution {
    public void flatten(TreeNode root) {
        if(root == null)    return ;
        if(root.left == null){
            flatten(root.right);
            return ;
        }
        TreeNode pre = root.left;
        while(pre.right != null ){
            pre = pre.right;
        }
        pre.right = root.right;
        root.right = root.left;
        root.left = null;
        flatten(root.right);
    }
}




// 非递归写法
public class Solution {  
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {        // 左子树不为空
                TreeNode pre = root.left;
                while (pre.right != null)   // 找到左子树中最右边的 
                    pre = pre.right;
                pre.right = root.right;      // 找到的这个结点的右子树是当前根的右子树 
                root.right = root.left;      // 当前根的右子树是当前根的左子树 
                root.left = null;          // 当前根的左子树为null
            }
            root = root.right;         //  向右更新 root   记得更新根是在最后，此时根的左子树已经变成了右子树 
        }
    }    
}



