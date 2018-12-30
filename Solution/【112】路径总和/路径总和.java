/*
* 深刻理解这个题目，其实也就是一个深度优先搜索，也可以说中序遍历。
* 要想清楚这种题目的逻辑，访问结点操作也就是我们所需要的操作
*/


class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) 
            return false;
        if(root.left == null && root.right == null){
            if(root.val == sum) 
                return true;
        }
        boolean result = false;
        result = hasPathSum(root.left , sum - root.val);  //往左子树走 
        if(result == true)    // 判断 
            return true;
        else{
            result = hasPathSum(root.right , sum - root.val);   // 右子树走 
        }
        return result;
    }
}


