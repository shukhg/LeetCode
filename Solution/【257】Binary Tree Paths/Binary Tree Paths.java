/*
就是一般的搜索并且组合字符串的代码
组合字符串一般都是在调用方法的时候对字符串进行添加
*/


class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root == null)
            return result;
        findPath(result , "" , root);
        return result;
    }
    public void findPath(List<String> result , String str , TreeNode root){
        if(root == null)
            return;
        if(root.left == null && root.right == null){
            str = str + root.val;
            result.add(str);
        }
        findPath(result , str  + root.val + "->" , root.left );
        findPath(result , str  + root.val + "->" , root.right);
    }
}