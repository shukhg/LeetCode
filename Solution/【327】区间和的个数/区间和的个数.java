/*
（1）利用前缀和数组 保存包括自身的前缀和
（2）先进行一遍遍历来保存前缀和，并且同时判断单个数字是否满足要求
（3）利用两个 for 循环，注意每个 i 更新后判断其 前缀和 是否满足要求
（4）内层 for 循环的时候，要注意 区间范围 的取值是去掉了 首（不去就是 前缀和 了）、尾（单个元素）
*/


//  利用前缀和，时间复杂度 O(n2)
class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length == 0 || lower > upper)   return 0;
        int count = 0;
        long[] sumArray = new long[nums.length];   // 前缀和，包括自身
        long sum = 0;
        for(int i = 0; i < nums.length; i ++){
            if(lower <= nums[i] && upper >= nums[i]){
                count ++;
            }
            sum = sum + nums[i];
            sumArray[i] = sum;
        }
        for(int i = 1; i < nums.length; i ++){
            if(lower <= sumArray[i] && upper >= sumArray[i]){
                count ++;
            }
            for(int j = 0; j < i - 1; j ++){  // z 的取值为 0-i 中除去首尾
                long z = sumArray[i] - sumArray[j];
                if(lower <= z && upper >= z){
                    count ++;
                }
            }
        }
        return count;
    }
}