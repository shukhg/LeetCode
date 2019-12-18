/*
【分析】
（1）选出根结点后应该先分别求解该根的左右子树集合，也就是根的左子树有若干种，它们组成左子树集合，根的右子树有若干种，它们组成右子树集合。
（2）然后将左右子树相互配对，每一个左子树都与所有右子树匹配，每一个右子树都与所有的左子树匹配。然后将两个子树插在根结点上。
（3）最后，把根结点放入list中

【注意】题目中的 List 存储的是根结点
要想明白，这个题目list 里面存储的是树，也就是所有树的所有结点，所以有很多 null 也算意料之中。此题甚至把叶子结点的左右子树都放入了list
*/


// 此题所有的结点都要加入到 result中的，因为同一棵树的不同结点有指针，所以可以找到不同的树
class Solution {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new ArrayList<>();
        if(n < 1){
            return result;
        }
        dfs(result, 1, n);
        return result;
    }
    public void dfs(List<TreeNode> result, int start, int end){
        if(start > end){  // start == end 的时候是有一个结点的
            result.add(null);    // null 也要添加进去
            return ;
        }
        for(int i = start; i <= end; i ++){   // 此处有 == 因为一个结点也可以
            List<TreeNode> left = new ArrayList<>();
            List<TreeNode> right = new ArrayList<>();
            dfs(left, start, i - 1);
            dfs(right, i + 1, end);
            for(TreeNode temp_left: left){
                for(TreeNode temp_right: right){
                    TreeNode root = new TreeNode(i);
                    root.left = temp_left;
                    root.right = temp_right;
                    result.add(root);
                }
            }
        }
    }
}




