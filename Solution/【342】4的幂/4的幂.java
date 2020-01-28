/*
判断一个数是否为 x 的幂就是用一个 while(n != 1) 一直循环，如果余数不为 0 则肯定不是，如果余数为 0 则 n = n / x 更新这个数
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