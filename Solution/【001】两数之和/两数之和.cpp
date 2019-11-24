

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        vector<int> result;
        map<int, int> my_map;
        for(int i = 0; i <= nums.size(); i++){
            int div = target - nums[i];
            if(my_map.find(div) != my_map.end()){
                result.push_back(my_map[div]);
                result.push_back(i);
                return result;
            }
            my_map[nums[i]] = i;
        }
        return result;
    }
};