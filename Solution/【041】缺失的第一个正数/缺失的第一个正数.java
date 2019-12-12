/*
（1）想清楚 if 语句中的逻辑，尤其是 nums[i] != nums[nums[i] - 1]。要保证交换前后不等，如果相等的话，两个相同的数一直交换，会造成死循环
（2）发生交换的话，不需要 i++的。以后记住这种交换都是发生交换就不需要 i++
*/

class Solution {
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0)    return 1;
        int len = nums.length;
        for(int i = 0; i < len; i ++){
            while(nums[i] >= 1 && nums[i] <= len && nums[nums[i] - 1] != nums[i]){
                swap(nums, i, nums[i] - 1);
            }
        }
        int index = 0;
        while(index < len && nums[index] == index + 1){
            index ++;
        }
        return index + 1;
    }
    public void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}