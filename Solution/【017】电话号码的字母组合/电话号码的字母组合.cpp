/*
（1）注意 map 初始化赋值的方式
（2）此处核心思想是：先添加一个 字符串，然后把这个字符串加入新的字符后删除前面的字符串
*/




class Solution {
public:
    vector<string> letterCombinations(string digits) {
        vector<string> result;
        if(digits.empty() || digits.size() == 0){
            return result;
        }
        map<char, string> my_map = {{'2', "abc"}, {'3', "def"}, {'4', "ghi"}, {'5', "jkl"}, {'6', "mno"}, {'7', "pqrs"}, {'8', "tuv"}, {'9', "wxyz"}};
        result.push_back("");
        for(int i = 0; i < digits.size() ; i ++){
            char num = digits.at(i);
            if(num == '1'){
                return result;
            }
            int len = result.size();
            for(int j = 0 ; j < len ; j ++){
                for (auto ch: my_map[num]){   //遍历按键对应字符
                    result.push_back(result[j] + ch);
                }
            }
            result.erase(result.begin(),result.begin()+len);
        }
        return result;
    }
};


