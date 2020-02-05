/*
1、动态规划解法
（1）将这组数看成两部分，P 和 N，其中 P 使用正号，N 使用负号，有 sum(P) - sum(N) = target
（2）因为 sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)，所以 2 * sum(P) = target + sum(nums)
（3）问题转化位 因此只要找到一个子集，令它们都取正号，并且和等于 (target + sum(nums)) / 2，就证明存在解
（4）这是典型的 01 背包问题

2、dfs 解法
（1）dfs 的过程遍历数组的每个元素，注意此处是不用写 for 循环的
（2）数组中每个元素分为两种情况，一种是 +  一种是 - 结果就返回两种的和 就可以了
（3）如果走到数组尽头，target == 0 则返回 1，否则返回 0
*/


// 转化为 01背包问题
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num: nums) {
            sum = sum + num;
        }
        if(sum < target || (sum + target) % 2 == 1){
            return 0;
        }
        target = (target + sum) / 2;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] + dp[i - num];

            }
        }
        return dp[target];
    }
}


// dfs，耗时多
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return findTargetSumWays(nums, 0, target);
    }
    public int findTargetSumWays(int[] nums, int start, int target) {
        if (start == nums.length) {
            if(target == 0) return 1;
            else    return 0;
        }
        return findTargetSumWays(nums, start + 1, target + nums[start]) 
        + findTargetSumWays(nums, start + 1, target - nums[start]);
    }
}