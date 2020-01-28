/*
（1）最简单是暴力法，直接遍历从 m 到 n 进行 & 操作即可，但是会超时
（2）因为需要遍历的数字是连续的一段序列，所以从左到右的位中，可以找到偏左边的一部分位是这段序列所共有的
（3）将 m 和 n 同时右移，并且记录右移位数，直到 m 和 n 相等，这样的找到了相同的位，然后将这些共同的位所代表的数再左移即可得到结果
*/

class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int count = 0;
        while(m != n){
            m = m >> 1;
            n = n >> 1;
            count ++;
        }
        return m << count;
    }
}