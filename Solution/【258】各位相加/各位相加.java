/*
一直循环，记录每一位的数即可
*/


class Solution {
    public int addDigits(int num) {
        int new_num;
        while(num / 10 != 0){
            new_num = 0;
            while(num != 0){
                new_num = num % 10 + new_num;
                num = num / 10;
            }
            num = new_num;
        }
        return num;
    }
}