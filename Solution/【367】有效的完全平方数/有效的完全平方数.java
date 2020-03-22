/*
（1）要注意这种题目可能越界，就所以能用 long 就用 long ，避免出现超过 int 的最大值。
（2）然后  mid * mid 的时候可能也会越界，所以最好用一个 long 类型的 midSq去存储 mid * mid 
*/

class Solution {
    public boolean isPerfectSquare(int num) {
        if(num == 0)
            return false;
        if(num == 1)
            return true;
        long left = 1;
        long right = num ;
        long mid ;
        long midSq ;
        while(left <= right){
            mid = (left + right) / 2;
            midSq = mid * mid;
            if( midSq == num)
                return true;
            else if( midSq < num )
                left = mid + 1;
            else
                right = mid - 1;
        }
        return false;
    }
}