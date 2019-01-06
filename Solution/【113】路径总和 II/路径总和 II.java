/*
标准的回溯解法，注意只能是叶子结点才满足要求。
这种回溯也尽量用 list.add()  list.remove() 的模板，容易理解
*/

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null )
            return result;
        if(root.left == null && root.right == null){
            if(root.val == sum){
                List<Integer> list = new ArrayList<Integer>();
                list.add(root.val);
                result.add(list );
                return result;
            }
        }
        List<Integer> list = new ArrayList<Integer>();
        list.add(root.val);
        search( root , sum , result , list );
        return result ;
    }
    public void search(TreeNode root , int sum , List<List<Integer>> result , List<Integer> list  ){
        if(root == null )
            return ;
        if(root.val == sum && root.left == null && root.right == null ){   //因为要叶子结点 
            result.add(new ArrayList<Integer>(list));
            return ;
        }
        if(root.left != null ){
            list.add(root.left.val);
            search( root.left , sum - root.val , result , list );
            list.remove(list.size() - 1);
        }
        if(root.right != null){
            list.add(root.right.val);
            search(root.right , sum - root.val , result , list);
            list.remove( list.size() - 1 );
        }
    }
}








