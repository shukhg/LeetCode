/*
此题和之前的题目一样，关键是要想清楚 root.left 和 root.right 构建时的下标
利用左子树和右子树的长度去思考这个问题更加合适
*/




class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null)
            return null;
        HashMap<Integer,Integer> hashmap = new HashMap<>();
        for(int i = 0 ; i < inorder.length ; i ++)
            hashmap.put(inorder[i] , i);
        TreeNode root = build(inorder , 0 , inorder.length - 1 , 
                             postorder , 0 , postorder.length - 1 , hashmap);
        return root;
    }
    public TreeNode build(int[] inorder , int inLeft , int inRight , int postorder[] , 
                         int postLeft , int postRight , HashMap<Integer,Integer> hashmap){
        if(inLeft > inRight || postLeft > postRight )
            return null;
        TreeNode root = new TreeNode(postorder[postRight]);
        int index = hashmap.get(root.val);
        root.left = build(inorder , inLeft , index - 1 , postorder,
                          postLeft , postLeft + index - inLeft - 1 ,hashmap );
        root.right = build(inorder , index + 1 , inRight , postorder , 
                          postRight - 1 - (inRight - index - 1 ) , postRight - 1 , hashmap );   // 可以利用 left的结果，也可以从postRight 去减
                        // 注意此处是 postRight - 1 - (inRight - index - 1 )  用长度去思考这种问题 
        return root ;
        
    }
}