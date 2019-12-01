/*
（1）转化成 long 和 abs，防止溢出和方便计算
（2）先将 被除数 左移放大至小于除数的最大值
（3）再把 被除数 逐步右移 并且记录结果
（4）判断结果的正负以及是否溢出
*/



class Solution {
public:
    int divide(int dividend, int divisor) {
        if(dividend == 0)   return 0;
        long long_dividend = dividend;    // 转化成 long，防止溢出
        long long_divisor = divisor;
        long cur_bit = 1, result = 0;
        long_dividend = abs(long_dividend);  // 取绝对值方便计算
        long_divisor = abs(long_divisor);
        while(long_dividend >= (long_divisor << 1)){   // 保证 被除数 <= 除数的情况下将被除数放大 cur_bit 倍
            long_divisor = long_divisor << 1;
            cur_bit = cur_bit << 1;
        }
        while(cur_bit > 0 && long_dividend > 0){   
            if(long_dividend >= long_divisor){ 
                long_dividend = long_dividend - long_divisor;
                result = result + cur_bit;
            }
            cur_bit = cur_bit >> 1;
            long_divisor = long_divisor >> 1;
        }
        result = ( (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0) )  ? -result:result;
        if(result > INT_MAX)    return INT_MAX;
        return (int)result;
    }
};