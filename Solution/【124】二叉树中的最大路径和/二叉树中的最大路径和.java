/*
（1）全局变量 result 记录结果，遇到每个结点的时候都判断 result 是否需要更新
（2）搜索的函数的功能是 找到 root 为根结点的左边 或者 右边 的一条最大路径，只能经过左边+根 或者 右边+根。因为可能有负值，所以需要判断与 0 的大小
*/



class Solution {
    int result = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        search(root);
        return result;
    }
    public int search(TreeNode root){   // 寻找 root分别与左右两子树走的最长路径
        if(root == null)    return 0;
        int left = search(root.left);
        int right = search(root.right);
        result = Math.max(left + right + root.val, result);  // 遇到每个结点都更新 result
        int left_right_max = Math.max(left, right);
        return Math.max(0, left_right_max + root.val);
    }
}

