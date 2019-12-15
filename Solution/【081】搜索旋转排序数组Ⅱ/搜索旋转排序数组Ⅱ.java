/*
以下是 33 题的
    直接使用二分法，判断那个二分点,有几种可能性

    1、直接等于target

    2、在左半边的递增区域
    a. target 在 left 和 mid 之间
    b. 不在之间

    3、在右半边的递增区域
    a. target 在 mid 和 right 之间
    b. 不在之间

此题因为存在重复元素，所以只需要想办法去除重复项的干扰即可。
10111 和 1110111101 这种。此种情况下 nums[start] == nums[mid]，分不清到底是前面有序还是后面有序，此时 left ++ 即可。相当于去掉一个重复的干扰项
*/



class Solution {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return false;
        }
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return true;
            }
            if(nums[left] == nums[mid]) {
                left ++;
                continue;
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
        return false;
    }
}
