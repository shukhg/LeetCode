/*
首先，题目给定了是32位的数字，所以不用考虑什么 while(n ！= 0) 直接用for循环遍历 0 到 31 即可
其次，分为奇数和偶数，因为奇数右移会丢失 1  
*/

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for(int i = 0 ; i < 32 ; i++){
            if(n % 2 == 1){
                result = (result << 1) + 1;
                n = n >> 1;
            }
            else{
                result = result << 1;
                n = n >> 1;
            }
        }
        return result;
    }
}