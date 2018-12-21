/*
递归地遍历左子树和右子树，注意往下层走的时候，只能往左子树+根 或者 右子树+根 的组合，而不能左子树加右子树加根。
但是判断当前最大值的时候，可以是左子树+根+右子树
遇到小于0 的，不要添加到当前值的组合中
比如左子树求的为负数，就 root.val + rightMax
*/


class Solution {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root == null )
            return 0;
        maxSum(root );
        return max;
    }
    public int maxSum(TreeNode root  ){
        int leftMax = -111111;   // 注意此处 要是从int最小值开始，传到下层会有错误
        int rightMax = -2221111;
        int value = root.val;
        if(root.left != null){
            leftMax = maxSum(root.left  );
            if(leftMax > 0 ){
                value = value + leftMax;
            }
        }
        if(root.right != null){
            rightMax = maxSum(root.right);
            if( rightMax > 0)
                value  = value + rightMax;
        }
        //max is the max of {root.val,root.val+lmax,root.val+rmax, root.val + lmax + rmax}
        if(value > max)
            max = value;
        
        //return max of (root.val, root.val + lmax, root.val + rmax)
        return Math.max(root.val,Math.max(root.val + leftMax , root.val + rightMax));
        //注意此处往下层走的时候只能往一边走，不能同时向左和向右
    }
}


