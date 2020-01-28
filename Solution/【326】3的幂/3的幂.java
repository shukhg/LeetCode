/*
判断一个数是否为 x 的幂就是用一个 while(n != 1) 一直循环，如果余数不为 0 则肯定不是，如果余数为 0 则 n = n / x 更新这个数
*/


class Solution {
    public boolean isPowerOfThree(int n) {
        if( n == 0 )
            return false;
        if( n == 1)
            return true;
        if( n == 2)
            return false;
        while( n != 1){
            if( n % 3 != 0 )
                return false;
            else{
                n = n / 3;
            }
        }
        return true;
    }
}
