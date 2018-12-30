/*
左子树相同并且右子树相同
*/

public class Solution{
	public static boolean isSameTree(TreeNode p , TreeNode q) {
        if( p == null && q == null) 
            return true;
        if( p == null || q == null) 
            return false;
        if(p.val != q.val)  
            return false;
        boolean judge_1 = isSameTree(p.left , q.left);
        boolean judge_2 = isSameTree(p.right , q.right);
        return judge_1 && judge_2;
	}
}