/*
注意 如果 x 一开始为 INT_MIN，那么  x = -x 的时候就会报错
*/



class Solution {
public:
    int reverse(int x) {
        int temp = x;
        if (x == INT_MIN){
            return 0;
        }
        if(x < 0){
            x = -x;
        }
        double result = 0;
        while(x != 0){
            result = result * 10 + x % 10;
            x = x / 10;
        }
        if(temp < 0){
            result = -result;
        }
        if(result > INT_MAX || result < INT_MIN){
            return 0;
        }
        else{
            return (int)result;
        }
    }
};