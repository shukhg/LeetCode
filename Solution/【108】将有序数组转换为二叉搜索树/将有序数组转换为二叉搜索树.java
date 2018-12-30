/*
每次找到 left 与 right 的中间点，递归地创建。
类似于深度优先搜索的代码风格
*/

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0)  
            return null;
        TreeNode root = create(nums , 0 , nums.length - 1);
        return root;
    }
    public TreeNode create(int[] nums , int left , int right){
        if(nums == null || nums.length == 0 ) 
            return null;
        int mid = (left + right) / 2;
        if(left > right)
            return null;
        
        mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = create(nums , left , mid - 1);
        root.right = create(nums , mid + 1 , right);
        return root;
    }
}