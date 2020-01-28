/*
第一趟循环移动非 0 的元素并且计数，第二趟循环从计数的 index 位置到数组末尾全部置 0
*/


class Solution {
    public void moveZeroes(int[] nums) {
        int index = 0;
        int n = nums.length;
        for(int i = 0 ; i < n ; i++){
            if(nums[i]!=0){
                nums[index ++] = nums[i];
            }
        }
        for(int i = index ; i < n ; i++){
            nums[i] = 0;
        }
    }
}
