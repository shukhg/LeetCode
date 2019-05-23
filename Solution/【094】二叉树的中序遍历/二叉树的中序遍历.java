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


class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pointer = root;
        while(pointer != null || stack.isEmpty() == false){
            while(pointer != null){
                stack.push(pointer);
                pointer = pointer.left;
            }
            if(stack.isEmpty() == false){
                pointer = stack.pop();
                result.add(pointer.val);
                pointer = pointer.right;
            }
        }
        return result;
    }
}