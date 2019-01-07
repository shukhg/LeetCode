/*
简单循环
*/

class Solution {
    public boolean isPowerOfFour(int num) {
        if( num == 0)
            return false;
        if( num == 1)
            return true;
        if( num == 2)
            return false;
        if( num == 3)
            return false;
        while( num != 1 ){
            if( num % 4 != 0)
                return false;
            else
                num = num / 4 ;
        }
        return true;
    }
}