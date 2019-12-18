/*
（1）使用颜色标记节点的状态，新节点为白色，已访问的节点为灰色。
（2）如果遇到的节点为白色，则将其标记为灰色，然后将其右子节点、自身、左子节点依次入栈。
（3）如果遇到的节点为灰色，则将节点的值输出。
注意因为使用了 栈，所以顺序是反的，先 right 然后 mid 最后 left
*/




// 
class Solution {
    class ColorNode {
        TreeNode node;
        String color;

        public ColorNode(TreeNode node, String color){
            this.node = node;
            this.color = color;
        }
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null)    return result;
        Stack<ColorNode> stack = new Stack<>();
        stack.push(new ColorNode(root, "white"));
        while(!stack.empty()){
            ColorNode cn = stack.pop();
            if(cn.color.equals("white")){
                if(cn.node.right != null){  // 因为是利用栈，所以先右后左
                    stack.push(new ColorNode(cn.node.right, "white"));
                }
                stack.push(new ColorNode(cn.node, "gray"));
                if(cn.node.left != null){
                    stack.push(new ColorNode(cn.node.left, "white"));
                }
            }
            else{
                result.add(cn.node.val);
            }
        }
        return result;
    }
}




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