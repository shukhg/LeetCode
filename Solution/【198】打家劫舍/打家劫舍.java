/*
（1）因为相邻的房间不可进入，所以在当前位置 n 房屋可盗窃的最大值，要么就是 n-1 房屋可盗窃的最大值，要么就是 n-2 房屋可盗窃的最大值加上当前房屋的值，二者之间取最大值
（2）也就有了状态转移方程 dp[i]=Math.max(dp[i-1] , dp[i-2] + nums[i])
（3）不要去想 dp[n] 在选择 dp[i-1] , dp[i-2] + nums[i] 的时候，可能是 dp[i - 2] 没有进入房间的问题，因为这个问题在计算 dp[i-1] 的时候就考虑了
*/


class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0)    return 0;
        if(nums.length <= 1)    return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], dp[0]);
        for(int i = 2; i < nums.length; i ++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2]+ nums[i]);
        }
        return dp[nums.length - 1];
    }
}