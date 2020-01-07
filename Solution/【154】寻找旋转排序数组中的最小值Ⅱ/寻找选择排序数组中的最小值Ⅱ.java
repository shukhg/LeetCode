/*
当nums[mid] > nums[right] 说明 mid 左半边是递增区域, 也就说明最小元素在 > mid 的区域
当nums[mid] < nums[right] 说明 mid 右半边是递增区域, 也就说明最小元素在 <= mid 的区域（有 = 是因为可能递增的部门就一个元素）
当nums[mid] == nums[right] 则 right -- 去除重复的影响
*/


class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[right] == nums[mid]){
                right --;
            }
            else if(nums[right] > nums[mid]){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }
        return nums[left];
    }
}