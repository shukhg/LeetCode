


class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {

        vector<int> result;
        map<int, int> hashmap;
        for(int i = 0; i < nums.size(); i ++){
            int div = target - nums[i];
            if(hashmap.find(div) != hashmap.end()){
                result.push_back(hashmap[div]);
                result.push_back(i);
                return result;
            }
            hashmap[nums[i]] = i;
        }
        return result;
    }
};