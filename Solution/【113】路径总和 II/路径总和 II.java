/*
标准的回溯解法，注意只能是叶子结点才满足要求。
这种回溯也尽量用 list.add()  list.remove() 的模板，容易理解
*/

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)    return result;
        search(result, new ArrayList<Integer>(), root, sum);
        return result;
    }
    public void search(List<List<Integer>> result, List<Integer> temp, TreeNode root, int sum){
        if(root == null)    return ;
        if(root.val == sum && root.left == null && root.right == null){
            temp.add(root.val);
            result.add(new ArrayList<>(temp));
            temp.remove(temp.size() - 1);   // 注意这里也要 remove，只要有 add 一定要有 remove
            return ;
        }
        temp.add(root.val);
        search(result, temp, root.left, sum - root.val);
        search(result, temp, root.right, sum - root.val);
        temp.remove(temp.size() - 1);
    }
}








