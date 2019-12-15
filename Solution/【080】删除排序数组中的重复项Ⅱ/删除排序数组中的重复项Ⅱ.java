/*
既然是最多出现两次，那么可以 index - 2 位置的元素与 i 位置的元素比较，如果相同的话，那就说明该元素已经出现过两次或者以上了，那就不要了
*/



// 最简单的解法
class Solution {
    public int removeDuplicates(int[] nums) {
        int index = 0;
        for(int i = 0; i < nums.length; i ++){
            if(index < 2 || nums[index - 2] != nums[i]){
                nums[index] = nums[i];
                index ++;
            }
        }
        return index;
    }
}






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

