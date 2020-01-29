/*
（1）因为是二叉搜索中，而且要找第 k 小，所以是用中序遍历
（2）套用中序遍历的模板，加个 count 来记录当前遍历的结点次序即可
*/




class Solution {
    int count;
    int result;
    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        inOrder(root, k);
        return result;
    }
    public void inOrder(TreeNode root, int k){
        if(root == null)    return ;
        if(root.left != null){
            inOrder(root.left, k);
        }
        count ++;
        if(count == k){
            result = root.val;
        }
        if(root.right != null){
            inOrder(root.right, k);
        }
    }
}