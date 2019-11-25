


class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        if(s.empty() || s.size() == 0){
            return 0;
        }
        int index = 0;
        int result = 0;
        map<char, int> hashmap;
        for(int i = 0 ; i < s.length() ; i ++){
            char ch = s.at(i);
            if(hashmap.find(ch) != hashmap.end()){
                index = max(index, hashmap[ch] + 1);
            }
            hashmap[ch] = i;
            result = max(result, i - index + 1);
        }
        return result;
    }
};

