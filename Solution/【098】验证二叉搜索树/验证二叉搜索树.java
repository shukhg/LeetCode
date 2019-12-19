/*
显然是中序遍历，但是要注意，要判断遍历的时候前一个结点的值比后一个结点的值小
但是实现方式可以不同，其实没必要利用一个  list 去存储中序遍历的结果，只需要模拟中序遍历的过程然后利用一个 pre 指针保存中序遍历的上一个结点即可
*/


class Solution {
    TreeNode pre = null;    // 用 pre 记录中序遍历的上一个结点
    public boolean isValidBST(TreeNode root) {
        if(root == null)    return true;
        boolean judge_left = isValidBST(root.left);
        if(judge_left == false) return false;
        if(pre != null && pre.val >= root.val)  return false;
        pre = root;
        boolean judge_right =isValidBST(root.right);
        return judge_right;
    }
}






// 利用 list 存储
class Solution {
    List<Integer> list = new ArrayList<>();   // 注意此处是全局的 list
    public boolean isValidBST(TreeNode root) {
        if( root == null)
            return true;
        if( root.left == null && root.right == null)
            return true;
        inOrder( root );
        for(int i = 1 ; i < list.size() ; i ++ ){    // 遍历 list就可以判断了
            if(list.get(i) <= list.get(i - 1) )    // 注意此处有 = 
                return false;
        }
        return true;
    }
    
    public void inOrder(TreeNode root ){
        if(root == null)
            return ;
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }
}