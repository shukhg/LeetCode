/*
思路也不难，就dfs然后判断是否是左边叶子节点即可
但是发现一个问题，就是Java中值传递的问题
*/

//  这个代码是可以通过的，因为用数组传递，如果把数组改为 int数字 就过不了了
class Solution {
    
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null )
            return 0;
        if(root.left == null && root.right == null)
            return 0;
        int[] sum = new int[2];
        search(root , sum);
        return sum[0];
    }
    public void search(TreeNode root , int[] sum){
        if(root == null)
            return ;
        if(root.left != null && root.left.left == null && root.left.right == null){
            sum[0] = sum[0] + root.left.val;
        }
        else{
            search(root.left , sum);
        }
        search(root.right , sum);
    }
}

// 要处理这个问题，就可以这样。用全局变量，但是不把全局变量传入到参数
// 记住千万不能把全局变量传递到参数，不然就和普通变量一样了
class Solution {
    int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null )
            return 0;
        if(root.left == null && root.right == null)
            return 0;
        sum = 0;
        search(root);
        return sum;
    }
    public void search(TreeNode root ){
        if(root == null)
            return ;
        if(root.left != null && root.left.left == null && root.left.right == null){
            sum = sum + root.left.val;
        }
        else{
            search(root.left);
        }
        search(root.right );
    }
}