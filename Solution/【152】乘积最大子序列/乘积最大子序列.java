/*
用动态规划解决，找出状态转移方程最关键。
由于乘积可能为负数，负负得正，因此第i-1次的乘积最大值（max_Value_Pre）与最小值（min_Value_Pre）都需要保留。
当然也可以定义最大值最小值数组来保存第i次乘积的最大值（max_Value）与最小值（min_Value）。
与Maximum Subarray相比，最大值为max_Value = Math.max(min_Value_Pre * nums[i] , Math.max(max_Value_Pre * nums[i] , nums[i]))
最小值为  min_Value = Math.min(min_Value_Pre * nums[i] , Math.min(max_Value_Pre * nums[i] , nums[i]))
*/


class Solution {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int max_Value ;    // 存储当前的最大值
        int min_Value ;         // 存储当前的最大值
        int result = nums[0];        
        int max_Value_Pre = nums[0];     // 存储遍历到上一个元素时候的最大值
        int min_Value_Pre = nums[0];     // 存储遍历到上一个元素时候的最小值 
        for(int i = 1 ; i < nums.length ; i++){
            max_Value = Math.max(min_Value_Pre * nums[i] , Math.max(max_Value_Pre * nums[i] , nums[i]));
            min_Value = Math.min(min_Value_Pre * nums[i] , Math.min(max_Value_Pre * nums[i] , nums[i]));
            if(max_Value > result)
                result = max_Value;
            max_Value_Pre = max_Value;
            min_Value_Pre = min_Value;
        } 
        return result;
    }
}