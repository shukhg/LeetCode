/*
递归的求就可以了
*/

class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null)    return 0;
        if(root.left == null && root.right == null) return 1;
        int left_depth = maxDepth(root.left);
        int right_depth = maxDepth(root.right);
        return Math.max(left_depth, right_depth) + 1;
    }
}