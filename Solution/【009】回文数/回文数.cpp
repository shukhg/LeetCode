

class Solution {
public:
    bool isPalindrome(int x) {
        if(x < 0){
            return true;
        }
        else if(x < 10){
            return false;
        }
        double invert = 0;
        int temp = x;
        while(x != 0){
            invert = invert * 10 + x % 10;
            x = x / 10;
        }
        if(invert == temp){
            return true;
        }
        else{
            return false;
        }
    }
};



