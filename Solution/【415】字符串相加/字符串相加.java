/*
（1）涉及到多次字符串的拼接，最好使用 StringBuilder
（2）每次从里面拿出一个 位 来相加，如果某个数遍历完了就取 0
（3）如果最后的 进位 == 1 的话，则将其添加到结果中
（4）记得最后 StringBuilder 需要反转 
*/




class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder("");
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while(i >= 0 || j >= 0){
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            result.append(tmp % 10);
            i--; j--;
        }
        if(carry == 1) result.append(1);
        return result.reverse().toString();
    }
}
