/*
非递归代码要完全能够背出来，要点如下
（1）使用栈
（2）while循环是 栈不为空 或 指针不为空 满足一个即可
（3）内层 while循环是直到指针为空才跳出，操作是向左走并且进栈
（4）指针为空了，就要判断栈是否还为空，不为空则出栈然后向右边走
*/

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