



class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        if(strs.empty() || strs.size() == 0 || strs[0].size() == 0){
            return "";
        }
        string result = "";
        string short_str = strs[0];
        for(int i = 1; i < strs.size() ; i ++){
            string long_str = strs[i];
            if(long_str.size() == 0){
                return "";
            }
            if(short_str.size() > long_str.size()){
                string temp = short_str;
                short_str = long_str;
                long_str = temp;
            }
            while(long_str.find(short_str) != 0){    // 公共前缀，所以应该是 0
                short_str = short_str.substr(0, short_str.size() - 1);
            }
        }
        return short_str;
    }
};


