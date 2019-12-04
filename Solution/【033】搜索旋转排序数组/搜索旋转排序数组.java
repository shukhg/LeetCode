/*
此题要记住
判断是否满足用 mid 去判断。 然后后面的两个判断就是判断 left 和 mid  以及 right  和 mid 对应元素的大小
如果 nums[left] <= nums[mid] 则表面左半部分是正常的，转折点肯定在右边
如果不满足上式，则表明转折点肯定在左边
if 里面的话，分为在范围中和在范围外
*/


class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;
        int left = 0 , right = nums.length - 1;
        int mid = left + (right - left) / 2;
        while(left <= right){
            mid = left + (right - left ) / 2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[left] <= nums[mid]){     // 记住如果这里是 left <= mid，这个if 里面判断都是用 left
                if(nums[left] <= target && nums[mid] >= target){   // 在left 和 mid 之间
                    right = mid - 1;                         
                } 
                else{                     // 在mid 的右边
                    left = mid + 1;
                }
            }
            else{          // 之前的 if 用了 left，这里就用 right
                if(nums[right] >= target && nums[mid] <= target){   // 在 mid 和 right 之间
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
