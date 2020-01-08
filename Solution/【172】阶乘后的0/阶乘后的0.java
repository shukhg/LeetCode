/*
（1）考虑有多少个 5 就可以了
（2）0、5、15、20、25  发现每隔 5个数字出现 1 个5，每隔 25个数字出现 2个5（25可以拆分为 5 * 5），依次类推
（3）所以需要类似求 n 进制 这样循环 n = n / 5
*/


class Solution {
    public int trailingZeroes(int n) {
        int count = 0;
        while( n != 0){
            count = count + n / 5;
            n = n / 5;
        }
        return count ;
    }
}