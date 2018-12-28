/*
【分析】
采用动态规划，因为分解的子问题之间是有联系的。
1. 选出根结点后应该先分别求解该根的左右子树集合，也就是根的左子树有若干种，它们组成左子树集合，根的右子树有若干种，它们组成右子树集合。
2. 然后将左右子树相互配对，每一个左子树都与所有右子树匹配，每一个右子树都与所有的左子树匹配。然后将两个子树插在根结点上。
3. 最后，把根结点放入list中

【注意】题目中的 List 存储的是根结点
要想明白，这个题目list 里面存储的是树，也就是所有树的所有结点，所以有很多 null 也算意料之中。此题甚至把叶子结点的左右子树都放入了list
*/


// 此题所有的结点都要加入到 result中的，因为同一棵树的不同结点有指针，所以可以找到不同的树
class Solution {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        if( n < 1 )
            return result;
        generate( result ,  1 , n   );
        return result;
    }
    public void  generate( List<TreeNode> result ,  int start , int end  ){
        if( start > end ){             //  start = end 也是可以的 
            result.add(null);         // 注意此处找到一个null结点也要添加进 result，还有叶子及其子女
            return ;
        }
        for(int i = start ; i <= end ; i ++ ){         // 此处有 =  就算是只有一个结点也可以生成根
            List<TreeNode> left = new ArrayList<>(); 
            List<TreeNode> right = new ArrayList<>(); 
            generate( left , start , i - 1);       //  得到左子树的各种组合
            generate( right , i + 1 , end );        //  得到右子树的各种组合 
            for(  TreeNode temp_left : left){           // 每一个左子树 和 全部的右子树匹配 
                for (TreeNode temp_right : right ){
                    TreeNode root = new TreeNode(i);
                    root.left = temp_left;
                    root.right = temp_right;
                    result.add(root);
                }
            }
        }  
    }
}




