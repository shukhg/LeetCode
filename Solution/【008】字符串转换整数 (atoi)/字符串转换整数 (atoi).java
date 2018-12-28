/*
1. 首先需要丢弃字符串前面的空格；
2. 然后可能有正负号（注意只取一个，如果有多个正负号，那么说这个字符串是无法转换的，返回0 	比如测试用例里就有个“+-2”）；
3. 字符串可以包含0~9以外的字符，如果遇到非数字字符，那么只取该字符之前的部分，如“-00123a66”返回为“-123”；
4. 如果超出int的范围，返回边界值（2147483647或-2147483648）。
5. 注意字符转化为整数的方法， (int)str.charAt(i) - '0'   此点一定要记住 
*/




class Solution {
    public int myAtoi(String str) {
        str = str.trim();     //这个一定要在第一步
        if(str == null || str.length() == 0)
            return 0;
        boolean flag  = true;
        double result = 0;    //此处要 double
        int i = 0;
        if(str.charAt(i) == '-'){
            flag = false;
            i = i + 1;
        }
        else if(str.charAt(i) == '+'){
            flag = true;
            i = i + 1;
        }
        for( ; i < str.length() ; i ++){
            int  c = (int)str.charAt(i) - '0';
            if( c < 0 || c > 9){
                break;
            }
            if(flag == true){
                result = result * 10 + c;
                if(result > Integer.MAX_VALUE)
                    return Integer.MAX_VALUE;
            }
            else{
                result = result * 10 - c;
                if(result < Integer.MIN_VALUE)
                    return Integer.MIN_VALUE;
            }
        }
        return (int)result;
    }
}