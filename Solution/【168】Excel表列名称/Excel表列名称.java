/*
（1）进制转换的代码：取 n % 26，然后根据此余数变成字符
（2）对于 26 的倍数，其余数为 0，但是实际对应的字符为 'Z'，可以分开处理
*/




class Solution {
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while(n > 0){
            int num = n % 26;
            if(num == 0){  // 处理 26 的倍数的转化
                num = 26;
                n = n - 1;
            }
            char ch = (char)('A' + num - 1);
            result.insert(0, ch);
            n = n / 26;
        }
        return result.toString();
    }
}