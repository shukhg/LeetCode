/*
重点是两个剔除重复结果的操作
if(i > 0 && nums[i] == nums[i - 1])   和   if (j > i + 1 && nums[j - 1] == nums[j])
*/




class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        vector<vector<int>> result;
        if(nums.empty() || nums.size() == 0){
            return result;
        }
        sort(nums.begin(), nums.end());
        for(int i = 0; i < nums.size(); i ++){
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            for(int j = i + 1; j < nums.size(); j ++){
                if (j > i + 1 && nums[j - 1] == nums[j]){
                    continue;
                }
                int k = j + 1;
                int m = nums.size() - 1;
                while(k < m){
                    if(nums[i] + nums[j] + nums[k] + nums[m] == target){
                        result.push_back({nums[i], nums[j], nums[k], nums[m]});
                        while(k < m && nums[k] == nums[k + 1]){
                            k ++;
                        }
                        while(k < m && nums[m] == nums[m - 1]){
                            m --;
                        }
                        k ++;
                        m --;
                    }
                    else if(nums[i] + nums[j] + nums[k] + nums[m] < target){
                        k ++;
                    }
                    else{
                        m --;
                    }
                }
            }
        }
        return result;
    }
};

