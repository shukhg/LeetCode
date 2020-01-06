/*
模板的方法，记住顺序是按照出栈的顺序
*/



// 模板
class Solution {
    class ColorNode {
        TreeNode node;
        String color;

        public ColorNode(TreeNode node, String color){
            this.node = node;
            this.color = color;
        }
    }
    public List<Integer> preorderTraversal(TreeNode root) {
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
                if(cn.node.left != null){
                    stack.push(new ColorNode(cn.node.left, "white"));
                }
                stack.push(new ColorNode(cn.node, "gray"));
            }
            else{
                result.add(cn.node.val);
            }
        }
        return result;
    }
}






// 非递归代码
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null)
            return result;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pointer = root;
        while(stack.isEmpty() == false || pointer != null ){   // 记住是栈不为空 和 指针不空 满足一个就可以
            while( pointer != null ){
                result.add(pointer.val);
                stack.push(pointer);
                pointer = pointer.left;
            }
            if(stack.isEmpty() == false){
                pointer = stack.pop();
                pointer = pointer.right;
            }
        }
        return result;
    }
}


//这是递归代码
class Solution {
    List<Integer> result = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null)
            return result;
        result.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return result;
    }
}