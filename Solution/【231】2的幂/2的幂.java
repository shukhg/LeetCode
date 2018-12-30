/*
（1）负数肯定不是
（2）奇数肯定不是
（3）初始化 long 类型的temp = 1，然后逐步左移与 n 进行比较
*/

class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0)
            return false;
        if(n == 1) return true;
        if(n % 2 == 1) return false;
        long temp = 1;
        while(temp <= n){
            if(temp == n){
                return true;
            }
            else{
                temp = temp << 1 ;
            }
        }
        return false;
    }
}