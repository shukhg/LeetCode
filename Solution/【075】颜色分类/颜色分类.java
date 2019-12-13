/*
三个指针 left 、right 、pointer ， left 左边全部为 0 ， right 右边全部为 2 , left与right中间（包括本身）全部为 1 
*/


class Solution {
    public void sortColors(int[] nums) {
        int pointer = 0, left = 0, right = nums.length - 1;
        while(pointer <= right){
            if(nums[pointer] == 1){
                pointer ++;
            }
            else if(nums[pointer] == 0){
                swap(nums, pointer, left);
                left ++;
                pointer ++;
            }
            else{   // 因为 left 被交换的一定不是0，但是 right 被交换的可能是 2，所以此处不更新 pointer
                swap(nums, pointer, right);
                right --;
            }
        }
    }
    public void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}