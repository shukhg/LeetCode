/*
（1）利用双指针实现滑动窗口。如果窗口内的 thisSum >= target 就更新 result 并且将 left ++ ; 如果窗口内的 thisSum < target 就将 right ++
（2）注意 right ++ 的时候的越界问题
（3）注意 result 初始化后可能没有更新 result，这个时候应该返回 0 而不是 result初始化的值
*/


class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0)    return 0;
        int left = 0, right = 0;
        int result = Integer.MAX_VALUE;
        int thisSum = nums[0];
        while(left < nums.length && right < nums.length){
            if(thisSum >= s){
                result = Math.min(result, right - left + 1);
                thisSum = thisSum - nums[left];
                left ++;
            }
            else{
                right ++;
                if(right < nums.length) thisSum = thisSum + nums[right];
            }
        }
        if(result == Integer.MAX_VALUE) return 0;
        else    return result;
    }
}