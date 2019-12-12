





class Solution {
public:
    vector<int> searchRange(vector<int>& nums, int target) {
        vector<int> result(2, -1);
        if(nums.empty() || nums.size() == 0){
            return result;
        }
        result[0] = searchLeft(nums, target, 0, nums.size() - 1);
        result[1] = searchRight(nums, target, 0, nums.size() - 1);
        return result;
    }
    int searchLeft(vector<int>& nums, int target, int left, int right){
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                if(mid == 0 || nums[mid - 1] != nums[mid]){
                    return mid;
                }
                else{
                    right = mid - 1;
                }
            }
            else if(nums[mid] < target){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        return -1;
    }
    int searchRight(vector<int>& nums, int target, int left, int right){
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                if(mid == nums.size() - 1 || nums[mid + 1] != nums[mid]){
                    return mid;
                }
                else{
                    left = mid + 1;
                }
            }
            else if(nums[mid] < target){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        return -1;
    }
};