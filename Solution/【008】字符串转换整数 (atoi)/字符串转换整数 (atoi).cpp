/*
注意自己手动去除开始空格的方法
*/





class Solution {
public:
    int myAtoi(string str) {
        int i = 0;
        while (i < str.size() && str[i] == ' '){
            i ++;
        }
        str = str.substr(i);
        i = 0;
        if(str.empty() || str.size() == 0){
            return 0;
        }

        bool flag = false;
        long result = 0;
        if(str.at(i) == '-'){
            flag = true;
            i ++;
        }
        else if(str.at(i) == '+'){
            i ++;
        }
        for(; i < str.size() ; i ++){
            int num = str.at(i) - '0';
            if(num < 0 || num > 9){
                return result;
            }
            if(flag == false){
                result = result * 10 + num;
                if(result > INT_MAX){
                    return INT_MAX;
                }
            }
            else{
                result = result * 10 - num;
                if(result < INT_MIN){
                    return INT_MIN;
                }
            }
        }
        return (int)result;
    }
};

