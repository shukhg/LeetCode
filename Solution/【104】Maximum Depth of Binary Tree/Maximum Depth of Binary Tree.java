/*
递归的求就可以了
*/

class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) 
            return 0;
        if(root.left == null && root.right == null) 
            return 1;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if(left > right){
            return left + 1;
        }
        else{
            return right + 1;
        }
    }
}