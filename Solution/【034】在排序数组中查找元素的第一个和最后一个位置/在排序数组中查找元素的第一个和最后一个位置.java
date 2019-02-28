/*
二分法找到元素的起始位置和结束位置
*/


class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        if(nums == null || nums.length == 0){
            result[0] = -1;
            result[1] = -1;
            return result;
        }
        result[0] = search_Left(nums , target , 0 , nums.length - 1);
        result[1] = search_Right(nums , target , 0 , nums.length - 1);
        return result;
    }
    public int search_Left(int[] nums , int target , int left , int right){
        int mid = left + (right - left ) / 2;
        while(left <= right){
            if( nums[mid] == target ){
                if(mid - 1 >= 0 && nums[mid - 1] == target){
                    right = mid - 1;
                }
                else{
                    return mid;
                }
            }
            else if(nums[mid] > target){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
            mid = left + (right - left ) / 2;
        }
        return -1;
    }
    public int search_Right(int[] nums , int target , int left , int right){
        int mid = left + (right - left) / 2;
        if(left > right)
            return -1;
        if(nums[mid] == target){
            if(mid + 1 <= nums.length - 1 && nums[mid + 1] == target){
                return search_Right(nums , target , mid + 1 , right);
            }
            else{
                return mid;
            }
        }
        else if(nums[mid] > target){
            return search_Right(nums , target , left , mid - 1);
        }
        else{
            return search_Right(nums , target , mid + 1 , right);
        }
    }        
}