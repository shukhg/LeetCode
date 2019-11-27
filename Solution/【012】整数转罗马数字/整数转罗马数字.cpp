



class Solution {
public:
    const vector<int> store = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    const vector<string> strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    string intToRoman(int num) {
        if(num <= 0){
            return "";
        }
        int index = 0;
        string result = "";
        while(num != 0){
            string roman_str = strs[index];
            int roman_num = store[index];
            int count = num / roman_num;
            while(count != 0){
                result = result + roman_str;
                count --;
            }
            num = num % roman_num;
            index = index + 1;
        }
        return result;
    }
};


