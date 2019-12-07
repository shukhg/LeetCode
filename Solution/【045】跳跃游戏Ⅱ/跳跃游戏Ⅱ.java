/*
（1）利用  max_pos 记录当前一步可以到的最远处，下一步的遍历范围的最远处 end 就在当前一步的 max_pos
（2）如果到了当前一步的遍历范围的最远处，则 步数 + 1，并且更新当前一步的遍历范围 
*/





class Solution {
    public int jump(int[] nums) {
        int step = 0;
        int max_pos = 0;
        int end = 0;
        for(int i = 0; i < nums.length - 1; i ++){
            max_pos = Math.max(max_pos, nums[i] + i);
            if(i == end){
                end = max_pos;
                step ++;
            }
        }
        return step;
    }
}




