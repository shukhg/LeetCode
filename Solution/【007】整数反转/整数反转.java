/*
（1）Integer.MAX_VALUE      Integer.MIN_VALUE 
（2）注意整数反转的算法，其实就一个while循环，要理解
（3）用long的因为防止超过int的界限 
*/


class Solution {
    public int reverse(int x) {
        long result = 0;
        int temp = x;    // 用于暂存 x 正负值的判断
        if(x < 0){
            x = -x;
        }
        while(x != 0){
            result = result * 10 + x % 10;
            x = x / 10;
        }
        if(temp < 0){
            result = -result;
        }
        if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE){
            return 0;
        }
        return (int)result;
    }
}