/*
（1）对比之前类似的题目，中间都是用 nums[i]、nums[i-1]判断
（2）引入count 计数，判断是否大于2
*/


class Solution {
    public int removeDuplicates(int[] nums) {
        int count = 1;   //因为 i = 1开始
        int index = 1;  // 因为 i = 1开始
        if( nums.length <= 2)
            return nums.length;
        for(int i = 1 ; i < nums.length  ; i ++){
            if(nums[i] == nums[i - 1] && count < 2 ){  //与前一个相同并且count < 2
                nums[index ] = nums[i];
                index ++;
                count ++;
            }
            else if( nums[i] == nums[i - 1 ] && count >= 2 ){   //与前一个相同但count >= 2
                continue;
            }
            else{    // 后一个与前一个不同
                nums[index] = nums[i];
                count = 1;
                index ++;
            }
        }
        return index ;
    }
}

