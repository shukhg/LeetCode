/*
贪心算法
注意在循环的过程中使循环变量别超过数组的界
*/



class Solution {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0){
            return true;
        }
        int max_dis = nums[0];
        int index = 0;
        for(int i = 1; i < nums.length; i ++){
            if(max_dis + index < i){
                return false;
            }
            if(i + nums[i] > index + max_dis){
                index = i;
                max_dis = nums[i];
            }
        }
        return true;
    }
}





class Solution {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length <= 1)
            return true;
        int max = nums[0];
        int i = 0;
        while(i <= max && i < nums.length ){
            if(i + nums[i] > max){
                max = i + nums[i];
            }
            i ++;
        }
        if(max >= nums.length - 1)
            return true;
        else
            return false;
    }
}