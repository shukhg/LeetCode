/*
（1）三个指针 left 、right 、pointer ， left 左边全部为 0 ， right 右边全部为 2 , left与right中间（包括本身）全部为 1 
（2）注意，外层循环的终止条件是 pointer <= right ， = 号是因为 right 右边才全是 2
（3）当遇到 2 并且更新的时候，记住 pointer 不要自增， 可以看例子  1 2 1 2 
*/


class Solution {
    public void sortColors(int[] nums) {
        if(nums == null || nums.length <= 1)
            return ;
        int left = 0 , right = nums.length - 1;
        int pointer = 0;
        while(pointer <= right){
            if(nums[pointer] == 1){
                pointer ++;
                continue;
            }
            else if(nums[pointer] == 0){
                swap(nums , pointer , left);
                left ++;
                pointer ++;
            }
            else{
                swap(nums , pointer , right);
                right --;
            }
        }
    }
    public void swap(int[] nums , int left , int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}