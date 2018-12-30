/*
首先，左右子树只能全为空或者全部为空
然后，左子树的右子树和右子树的左子树、左子树的左子树和右子树的右子树相同
*/

public class Solution {
    boolean isSymmetric(TreeNode root)
    {
        if(root == null )  return true;
        if(root.left == null && root.right == null)  
            return true;
        if(root.left == null || root.right == null ) 
            return false;
        return judge(root.left , root.right);
        
    }
    boolean judge(TreeNode left , TreeNode right){
        //if(left.val != right.val)  return false;
        if(left == null && right == null) 
            return true;
        if(left == null || right == null ) 
            return false;
        if(left.val != right.val)  return false;
        boolean left_judge = judge(left.left , right.right);
        boolean right_judge = judge(left.right , right.left);
        return left_judge && right_judge;
    }
}