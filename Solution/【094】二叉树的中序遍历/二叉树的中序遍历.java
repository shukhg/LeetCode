/*
简单的中序遍历的代码
*/

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root , result);
        return result;
    }
    public void inorder(TreeNode root , List<Integer> result){
        if(root == null)
            return ;
        if(root.left == null && root.right == null ){
            result.add(root.val);
            return ;
        }
        inorder(root.left , result  );
        result.add(root.val);
        inorder(root.right , result );
    }
}