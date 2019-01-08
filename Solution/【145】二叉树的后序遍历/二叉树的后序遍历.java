/*
利用 preNode 进行标记，标记之前访问的结点。
注意访问当前子树的根结点的时候，要判断其右子树是否被访问过
如果没有被访问过，就进入右子树，然后还是走到右子树的最左边
如果有被访问过，就访问当前结点
*/


class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null)
            return result;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        TreeNode preNode = null;
        while(curNode != null){    // 先一直往左边走
            stack.push(curNode);
            curNode = curNode.left;
        }
        while(stack.isEmpty() == false){    // 栈不空就循环
            curNode = stack.pop();             // 先取出栈顶 
            if(curNode.right != null && curNode.right != preNode){   // 如果右子树存在并且未被访问
                stack.push(curNode);             // 当前结点入栈
                curNode = curNode.right;         // 往右子树走
                while(curNode != null){          // 走到右子树的最左边
                    stack.push(curNode);
                    curNode = curNode.left;
                }
            }
            else{                                 // 右子树不存在或者右子树已经被访问 
                result.add(curNode.val);
                preNode = curNode;
            }
        }
        return result;
    }
}