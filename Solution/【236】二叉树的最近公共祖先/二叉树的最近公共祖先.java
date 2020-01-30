/*
（1）核心思想是：如果 p 和 q 都在左边就往左子树走，如果都在右边就往右子树走，否则表明当前的根结点就是所求
（2）很自然的想法是写一个 search 函数，判断 p 或者 q 是否存在于以某个结点为根结点的树中，每次遇到一个结点都尝试去搜索
（3）上述方法太耗时，而且很多结点被访问了多次。所以能不能想办法把 搜索 和 树本身的递归 这两个递归的过程结合起来呢
（4）两个递归的过程返回的结果是不一样的，如果要合并成一起，就要想办法实现两个返回结果
（5）递归终止条件：如果当前节点为空或等于 p 或 q，则返回当前节点。递归正常结束时返回的结果：题目所求的返回结果
（6）递归地遍历左右子树，拿到返回的结果。这个返回的结果有两种表示，一种是 p、q 结点，一种是找到的最终结果
（7）如果递归左右子树的结果 left、right 一个在 root 左边一个在右边，则表明 root 即为所求
（8）如果 left != null，则表明 left 的含义是找到的最终结果，所以返回 left。同理，返回 right
（9）本题优化解法的关键：把两个递归给统一起来，区分递归终止边界 和 最终返回结果，然后判断递归的返回结果是哪种类型（不用显式判断）
*/


class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null){
            return root;
        }
        if(left != null){
            return left;
        }
        else{
            return right;
        }
    }
}



/*
（1）判断 p q 是否存在于 root 的左右子树中
（2）如果 p q 同时存在于左子树 或者 右子树，则往 左子树 或者 右子树 走
（3）如果不是同时存在左子树或者右子树，则是 一左一右、一根一左、一根一右，表明此根节点为所求的结点
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)    return root;
        boolean p_judge_left = searchNode(root.left, p);
        boolean q_judge_left = searchNode(root.left, q);
        boolean p_judge_right = searchNode(root.right, p);
        boolean q_judge_right = searchNode(root.right, q);
        if(p_judge_left && q_judge_left){   // 都存在于左边
            return lowestCommonAncestor(root.left, p, q);
        }
        else if(p_judge_right && q_judge_right){   // 都存在于右边
            return lowestCommonAncestor(root.right, p, q);
        }
        else{    // 一左一右 或者 一根一左（右）
            return root;
        }
    }
    public boolean searchNode(TreeNode root, TreeNode node){   // 查找 node 在 root 为根节点的树中是否出现, node 不为空
        if(root == null)    return false;
        if(root.val == node.val) return true;
        if(searchNode(root.left, node) == true){
            return true;
        }
        else{
            return searchNode(root.right, node);
        }
    }
}