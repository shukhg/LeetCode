/*
（1）递归中序遍历二叉树，设置一个pre指针，记录当前节点中序遍历时的前节点，如果当前节点大于pre节点的值，说明需要调整次序。
（2）递归中序遍历二叉树，设置一个pre指针，记录当前节点中序遍历时的前节点，如果当前节点大于pre节点的值，说明需要调整次序，用全局变量 mistake1 和 mistake2 记录下来。
（3）相邻的两个数字交换，[ 1 2 3 4 5 ] 中 2 和 3 进行交换，[ 1 3 2 4 5 ]，这样的话只产生一组逆序的数字
    （正常情况是从小到大排序，交换后产生了从大到小），3 2  我们只需要遍历数组，找到后，把这一组的两个数字进行交换即可。
（4）不相邻的两个数字交换，[ 1 2 3 4 5 ] 中 2 和 5 进行交换，[ 1 5 3 4 2 ]，这样的话其实就是产生了两组逆序的数字对。5 3 和 4 2。
    所以我们只需要遍历数组，然后找到这两组逆序对，然后把第一组前一个数字和第二组后一个数字进行交换即完成了还原
*/

class Solution {
    
    TreeNode mistake1 , mistake2;    //全局变量存储2个错误的结点 
    TreeNode pre ;
    public void recoverTree(TreeNode root) {
        search( root);
        int temp = mistake1.val;
        mistake1.val = mistake2.val;
        mistake2.val = temp;
    }
    
    public void search(TreeNode root ){
        if( root == null )
            return ;
        search(root.left);
        if( pre != null && pre.val >= root.val){   // 和 098 的 pre 一样，这里也是 pre != null 然后判断 pre.val 和 root.val
            if(mistake1 == null){     // 初始状态
                mistake1 = pre;
                mistake2 = root;
            }
            else{    // 已经找到了 mistake1 现在去找出现另一次错误的 mistake2
                mistake2 = root;   
            }
        }
        pre = root ;    // 更新 pre
        search(root.right);
    }
}

