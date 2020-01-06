/*
套用模板，记得遍历的顺序是出栈的顺序
*/


class Solution {
    class ColorNode {
        TreeNode node;
        String color;

        public ColorNode(TreeNode node, String color){
            this.node = node;
            this.color = color;
        }
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null)    return result;
        Stack<ColorNode> stack = new Stack<>();
        stack.push(new ColorNode(root, "white"));
        while(!stack.empty()){
            ColorNode cn = stack.pop();
            if(cn.color.equals("white")){
                stack.push(new ColorNode(cn.node, "gray"));
                if(cn.node.right != null){  // 因为是利用栈，所以先右后左
                    stack.push(new ColorNode(cn.node.right, "white"));
                }
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