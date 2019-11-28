/*
（1）一定要先排序   sort(nums.begin(), nums.end())
（2）计算 abs 一定要在最开始
*/



class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        if(nums.empty() || nums.size() == 0){
            return 0;
        }
        int result = 0;
        int temp = INT_MAX;
        sort(nums.begin(), nums.end());
        for(int i = 0; i < nums.size(); i ++){
            int j = i + 1;
            int k = nums.size() - 1;
            while(j < k){
                if(abs(nums[i] + nums[j] + nums[k] - target) < temp){
                    temp = abs(nums[i] + nums[j] + nums[k] - target);
                    result = nums[i] + nums[j] + nums[k];
                }
                if(nums[i] + nums[j] + nums[k] == target){
                    return target;
                }
                else if(nums[i] + nums[j] + nums[k] > target){
                    k --;
                }
                else{
                    j ++;
                }
            }
        }
        return result;
    }
};