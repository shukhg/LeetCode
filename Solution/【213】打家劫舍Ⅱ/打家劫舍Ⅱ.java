/*
（1）在不偷窃第一个房子的情况下（即 nums[1:]nums[1:]），最大金额是 p1
（2）在不偷窃最后一个房子的情况下（即 nums[:n-1]nums[:n−1]），最大金额是 p2
（3）综合偷窃最大金额： 为以上两种情况的较大值，即 max(p1,p2)
*/




class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        return Math.max(myRob(nums, 0, nums.length - 2), 
                        myRob(nums, 1, nums.length - 1));
    }
    private int myRob(int[] nums, int left, int right) {
        int pre = 0, cur = 0, temp;
        for(int i = left; i <= right; i ++){
            int num = nums[i];
            temp = cur;
            cur = Math.max(pre + num, cur);
            pre = temp;
        }
        return cur;
    }
}
