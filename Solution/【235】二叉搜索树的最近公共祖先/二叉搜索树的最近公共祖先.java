/*
（1）从根节点开始遍历树
（2）如果节点 p 和节点 q 都在右子树上，那么以右孩子为根节点继续 1 的操作
（3）如果节点 p 和节点 q 都在左子树上，那么以左孩子为根节点继续 1 的操作
（4）如果条件 2 和条件 3 都不成立，这就意味着我们已经找到节 p 和节点 q 的最近公共祖先
上述操作可以很方便的改成 迭代，只需要一直  while(root != null) 然后把递归调用的部分改为  root = root.left 或者 root = root.right 即可
*/


//   递归
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int parentVal = root.val;
        int pVal = p.val, qVal = q.val;
        if(pVal < parentVal && qVal < parentVal){
            return lowestCommonAncestor(root.left, p, q);
        }
        else if(pVal > parentVal && qVal > parentVal){
            return lowestCommonAncestor(root.right, p, q);
        }
        else{
            return root;
        }
    }
}


//  迭代
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int pVal = p.val, qVal = q.val;
        while(root != null){
            int parentVal = root.val;
            if(pVal < parentVal && qVal < parentVal){
                root = root.left;
            }
            else if(pVal > parentVal && qVal > parentVal){
                root = root.right;
            }
            else{
                return root;
            }
        }
        return null;
    }
}