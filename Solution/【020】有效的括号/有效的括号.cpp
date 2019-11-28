


class Solution {
public:
    map<char, char> my_map = {{'(', ')'}, {'[', ']'}, {'{', '}'} };
    bool isValid(string s) {
        if(s.empty() || s.size() == 0){
            return true;
        }
        stack<char> my_stack;
        for(int i = 0; i < s.size(); i ++){
            if(my_map.find(s.at(i)) != my_map.end()){
                my_stack.push(s.at(i));
            }
            else{
                if(my_stack.size() == 0){
                    return false;
                }
                char stack_top = my_stack.top();
                if(my_map[stack_top] != s.at(i)){
                    return false;
                }
                else{
                    my_stack.pop();
                }
            }
        }
        if(my_stack.size() == 0){
            return true;
        }
        else{
            return false;
        }
    }
};