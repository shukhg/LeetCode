/*
深刻理解这个题目，其实也就是一个深度优先搜索
*/


class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)    return false;
        return search(root, sum);
    }
    public boolean search(TreeNode root, int sum){
        if(root == null)    return false;
        if(root.val == sum && root.left == null && root.right == null){
            return true;
        }
        boolean judge_left = search(root.left, sum - root.val);
        if(judge_left == true)  return true;
        boolean judge_right = search(root.right, sum - root.val);
        return judge_right;
    }
}


