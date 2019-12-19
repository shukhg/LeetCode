/*
要判断是否是平衡二叉树，要所有结点的左子树是平衡二叉树，并且所有结点的右子树是平衡二叉树
还要所有结点的左子树和右子树高度差的绝对值不超过1
既然要用树的高度，就要用前面求二叉树高度的方法（递归）
*/


class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null )  return true;
        if(root.left == null && root.right == null) return true;
        int depth_left = depth(root.left);
        int depth_right = depth(root.right);
        if( Math.abs(depth_left - depth_right) <= 1){   // 深度差的绝对值小于等于1 
            boolean judge_left =  isBalanced(root.left);
            boolean judge_right = isBalanced(root.right);
            return judge_left && judge_right;
        }
        return false;
    }
    public int depth(TreeNode root){         // 求树的高度  
            if(root == null)    return 0;
            if(root.left == null && root.right == null) return 1;
            int depth_left = depth(root.left);
            int depth_right = depth(root.right);
            if(depth_left < depth_right)    return depth_right + 1;
            else    return depth_left + 1;
    }
}