/*
直接使用二分法，判断那个二分点,有几种可能性

1、直接等于target

2、在左半边的递增区域
a. target 在 left 和 mid 之间
b. 不在之间

3、在右半边的递增区域
a. target 在 mid 和 right 之间
b. 不在之间
*/


class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[left] <= nums[mid]){
                if(nums[mid] >= target && nums[left] <= target){
                    right = mid - 1;
                }
                else{
                    left = mid + 1;
                }
            }
            else{
                if(nums[mid] <= target && nums[right] >= target){
                    left = mid + 1;
                }
                else{
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
