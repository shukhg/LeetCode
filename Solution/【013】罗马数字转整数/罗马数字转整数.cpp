


class Solution {
public:
    int romanToInt(string s) {
        if(s.empty() || s.size() == 0){
            return 0;
        }
        map<char, int> my_map;
        my_map['I'] = 1;
        my_map['V'] = 5;
        my_map['X'] = 10;
        my_map['L'] = 50;
        my_map['C'] = 100;
        my_map['D'] = 500;
        my_map['M'] = 1000;
        int result = 0;
        
        for(int i = 1 ; i < s.size() ; i ++){
            char before = s.at(i - 1);
            char current = s.at(i);
            int num_before = my_map[before];
            int num_current = my_map[current];
            if(num_before >= num_current){
                result = result + num_before;
            }
            else{
                result = result - num_before;
            }
        }
        int num_last = my_map[s.at(s.size() - 1)];
        result = result + num_last;
        return result;
    }
};