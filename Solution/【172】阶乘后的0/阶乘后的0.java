/*
考虑有多少个 5 就可以了
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