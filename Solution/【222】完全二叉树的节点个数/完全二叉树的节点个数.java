/*
（1）暴力解法，直接遍历整棵树，不区分是否为完全二叉树，没有利用到完全二叉树的性质
（2）完全二叉树是一层层填充节点，而且的从左到右
（3）统计 左子树 和 右子树 的深度，如果相同的话，则说明左子树被填充满了，也就是左子树有  1 ^ left_high == 1 << left 个节点，再对右子树进行递归 
（4）如果 深度不同，则说明 最下层的节点未填充满左子树，但是此时右子树的节点是满的（不过深度少 1），则再对左子树递归
*/



class Solution {
    public int countNodes(TreeNode root) {
        if(root == null){
           return 0;
        } 
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if(left == right){
            return countNodes(root.right) + (1 << left);
        }
        else{
            return countNodes(root.left) + (1 << right);
        }
    }
    public int countLevel(TreeNode root){
        int level = 0;
        while(root != null){
            level++;
            root = root.left;
        }
        return level;
    }
}