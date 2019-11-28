






class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int> > result;
        if(nums.empty() || nums.size() <= 2){
            return result;
        }
        sort(nums.begin(), nums.end());    // 升序排序
        for(int i = 0 ; i < nums.size(); i ++){
            int j = i + 1;
            if (i > 0 && nums[i] == nums[i - 1]){     // 避免出现重复
                continue;
            }
            int k = nums.size() - 1;
            while(j < k){
                if(nums[i] + nums[j] + nums[k] == 0){
                    result.push_back({nums[i], nums[j], nums[k]});
                    while(j < k && nums[j] == nums[j + 1]){    // 避免出现重复
                        j ++;
                    }
                    while(j < k && nums[k] == nums[k - 1]){
                        k --;
                    }
                    j ++;
                    k -- ;
                }
                else if(nums[i] + nums[j] + nums[k] > 0){
                    k -- ;
                }
                else{
                    j ++;
                }
            }
        }
        return result;
    }
};
