/*
（1）问题可以转化为：数组中是否可以找到一些元素之和为 数组全部元素之和的一半 target
（2）上述问题可以转化为 0-1背包问题，数组中每个元素之用一次，找到元素组合使得其和为 target
（3）所以，可以类似于 0-1背包，创建二维 dp数组，一维是 nums.length，表示每个数的使用情况，一维是 target+1 表示目标和
（4）注意边界条件，dp[0][0] = false，dp[0][nums[0]] 则要判断 num[0]与 target 的关系
（5）如果 j == nums[i] 则 dp[i][j] = true，如果 j > nums[i] 则 dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]]; j 不可小于 nums[i]
*/




class Solution {
    public boolean canPartition(int[] nums) {
        if(nums == null || nums.length == 0)    return false;
        int sum = 0;
        for(int i = 0; i < nums.length; i ++){
            sum = sum + nums[i];
        }
        if(sum % 2 == 1)    return false;
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length][target + 1];
        dp[0][0] = false;
        if(nums[0] <= target){
            dp[0][nums[0]] = true;
        }
        for(int i = 1; i < nums.length; i ++){
            for(int j = 0; j <= target; j ++){
                dp[i][j] = dp[i - 1][j];
                if(j == nums[i]){
                    dp[i][j] = true;
                    continue;
                }
                if(j > nums[i]){
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][target];
    }
}