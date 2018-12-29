/*
此题仅用一趟遍历，从前到后，thisSum < 0 就重置为 0
注意 thisSum 更新的代码一定要在两个 if 中间，不然会出错
首先，thisSum 更新的代码肯定要在  if(thisSum < 0) 后面，例如第1个元素 < 0 的情景
其次，thisSum 更新要在 sum 的更新前，例如最后1个元素参与更新的情况下
*/


class Solution {
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int thisSum = nums[0];   // 记录当前子序列和 
        int sum = nums[0];         // 记录结果
        for(int i = 1 ; i < nums.length ; i++){
            if(thisSum < 0){       // 当前子序列和小于0 就丢弃
                thisSum = 0;
            }
            thisSum = thisSum + nums[i];    // 这个语句的位置一定要对，一定要在两个 if 中间 
            if(thisSum >= sum)
                sum = thisSum;
        }
        return sum;
    }
}