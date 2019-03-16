/*
（1）利用两个数组，一个存储各个数字值，一个存储罗马数字字符串
（2）类似求几进制的形式运算进行处理
*/

class Solution {
    public String intToRoman(int num) {
        int[] nums = {1000 , 900 , 500 , 400 , 100 , 90 , 50 , 40 , 10 , 9 , 5 , 4 , 1};
        String[] strs = {"M" , "CM" , "D" , "CD" , "C" , "XC" , "L" , "XL" , "X" , "IX" , "V" , "IV" , "I"};
        String result = "";
        int index = 0 ;
        while(num != 0){    // 类似于进制转换
            int count = num / nums[index];
            num = num % (nums[index]);
            while(count > 0){
                result = result + strs[index];
                count --;
            }
            index ++; 
        }
        return result;
    }
}