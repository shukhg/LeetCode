/*
* 显然是中序遍历，但是要注意，要判断遍历的时候前一个结点的值比后一个结点的值小
*/

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