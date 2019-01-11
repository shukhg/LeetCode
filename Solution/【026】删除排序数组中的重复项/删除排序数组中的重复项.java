/*
利用索引 index 记录不相同的元素即可
*/


class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int index = 1;    // 记录不相同元素的个数，也就是索引
        for(int i = 1 ; i < nums.length ; i ++){
            if(nums[i] != nums[i - 1]){   // 如果相邻的两个元素不相同
                nums[index] = nums[i];
                index ++ ;
            }
        }
        return index ;
    }
}