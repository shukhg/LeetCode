/*
（1）利用快速排序的 patition。注意如果是 nums[left]、num[right] 和 key 的关系，此关系决定是 把小于 key 的放左边 还是 把大于 key 的放左边
（2）注意 left 的左边是 大于 key（不包括 left），right 的右边是 小于 key（不包括 right），而返回的是 left，所以是用返回值与 k - 1 进行比较
*/



class Solution {
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int index = patition(nums, left, right);
        while(index != k - 1){
            if(index > k - 1){
                right = index - 1;
                index = patition(nums, left, right);
            }
            else{
                left = index + 1;
                 index = patition(nums, left, right);
            }
        }
        return nums[k - 1];
    }
    public int patition(int[] nums, int left, int right){
        int key = nums[left];
        while(left < right){  
            while(left < right && nums[right] <= key){    // 更新此 <= 为 >= 则可以把小于 key 的放左边，现在是 大于 key 的放左边
                right --;
            }
            swap(nums, left, right);
            while(left < right && nums[left] >= key){
                left ++;
            }
            swap(nums, left, right);
        }
        return left;
    }
    public void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}