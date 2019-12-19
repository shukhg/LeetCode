/*
每次找到 left 与 right 的中间点，递归地创建。
*/

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0)    return null;
        TreeNode root = build(nums, 0, nums.length - 1);
        return root;
    }
    public TreeNode build(int[] nums, int left, int right){
        if(left > right)    return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }
}