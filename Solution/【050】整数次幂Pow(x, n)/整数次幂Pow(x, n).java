/*
利用二分法，每次计算当前计算结果的一半。注意 负数次方的时候，先将负数变为正数，然后底数取倒数
*/

class Solution {
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        if(n == 1)
            return x;
        int count = n / 2;
        if( n < 0){            // 幂 小于 0 就将其转化为大于0计算
            count = -count;
            x = 1 / x;
        }
        double result = myPow( x , count);  // 折半计算
        if(n % 2 == 0)
            return result * result;
        else
            return result * result * x;
    }
}