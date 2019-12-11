/*
（1）涉及到字符串的题，尽量用 StringBuilder。此处要用 字符串的反转，也是用 StringBuilder 比较合适
（2）sb.append() 的时候可以 append   数字、字符、字符串、空字符串
（3）注意 for 循环中两个变量的写法
（4）利用 carry 表示当前位的进位，利用 sum 表示当前位 的和
（5）注意最后跳出循环的时候，需要判断 carry 为 0 还是 1
*/






class Solution {
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder("");
        int carry = 0;
        for(int a_bit = a.length() - 1, b_bit = b.length() - 1; a_bit >=0 || b_bit >= 0; a_bit --, b_bit --){
            int sum = carry;
            sum = sum + (a_bit >= 0 ? a.charAt(a_bit) - '0': 0);
            sum = sum + (b_bit >= 0 ? b.charAt(b_bit) - '0': 0);
            result.append(sum % 2);
            carry = sum / 2;
        }
        result.append(carry == 1? carry: "");
        return result.reverse().toString();
    }
}