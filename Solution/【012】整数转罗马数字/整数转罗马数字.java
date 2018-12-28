/*
（1）利用两个数组，一个存储各个数字值，一个存储罗马数字字符串
（2）类似求几进制的形式运算进行处理
*/


class Solution {
    public String intToRoman(int num) {
        int[] base = new int[] {1000 , 900 , 500 , 400 , 100 , 90  , 50 , 40 , 10 , 9 , 5 , 4 ,  1};
        String[] roman = {"M" , "CM" , "D" , "CD" , "C" , "XC" , "L" , "XL" , "X" , "IX" , "V" , "IV" , "I"};
        
        String result = "";
        int i = 0 ;
        while( num != 0 ){             // 类似于进制转换
            int count = num / base[i] ; // 有几倍当前base
            num = num % base[i];      // 余数
            while( count > 0){         // 把这个几倍全部加上 
                result = result + roman[i];
                count --;
            }
            i ++;      // 往base数组右边移动，也就是更新单位 
        }
        return result;
    }
}