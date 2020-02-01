/*
【测试数组】（在样例上多加了两个数）
[10, 9, 2, 5, 3, 7, 101, 18, 4, 19]
【有效长度/队列/插入值】
1 [-inf, 10] <- 10
1 [-inf, 9] <- 9
1 [-inf, 2] <- 2
2 [-inf, 2, 5] <- 5
2 [-inf, 2, 3] <- 3
3 [-inf, 2, 3, 7] <- 7
4 [-inf, 2, 3, 7, 101] <- 101
4 [-inf, 2, 3, 7, 18] <- 18
4 [-inf, 2, 3, 4, 18] <- 4
5 [-inf, 2, 3, 4, 18, 19] <- 19

（1）初始化一个数组 top ，用于保存最长上升子序列，用 piles 记录长度
（2）遍历原数组，将当前值在 top数组 中进行二分插入
（3）如果 top 中元素都比它小，将它插到最后；否则，用它覆盖掉比它大的元素中最小的那个
（4）最终的 top 数组中的元素个数即为最长上升子序列的长度，但是 top 数组中的元素不为最长上升子序列的元素
*/


class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] top = new int[nums.length];
        int piles = 0;   // 牌堆数初始化为 0
        for(int i = 0; i < nums.length; i ++){
            int poker = nums[i];   // 要处理的扑克牌
            // 利用二分查找寻找将 poker 放入的堆
            int left = 0, right = piles;
            // 如果 top 中元素都比它小，将它插到最后；否则，用它覆盖掉比它大的元素中最小的那个
            while (left < right) {    // 
                int mid = (left + right) / 2;
                if (top[mid] > poker) {
                    right = mid;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if(left == piles)   piles ++;  // 如果 top 中元素都比它小，将它插到最后
            top[left] = poker;
        }
        return piles;
    }
}




/*
（1）dp[i] 表示以当前数字为结尾的最长递增子序列的长度
（2）状态转移方程为 dp[i] = Math.max(dp[i], dp[j] + 1)  此处的 j 是从 0 遍历到 i - 1
（3）最后返回的结果不是 dp[len - 1]，而是要重新遍历一遍数组从中选出最大的（或者中间记录一个 result）
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0)    return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int result = 1;
        for(int i = 1; i < nums.length; i ++){
            dp[i] = 1;
            for(int j = 0; j < i; j ++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}