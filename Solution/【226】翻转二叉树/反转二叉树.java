/*
先翻转左右子树，再对左右子树进行递归处理
*/


class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return root;
        if(root.left == null && root.right == null)
            return root;
        if(root.left != null || root.right != null){
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }
}