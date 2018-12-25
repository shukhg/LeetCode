/*
本题的思路就是：
    设置两个指针，左边一个右边一个，一起向中间靠拢。
如果字符串长度小于2，肯定是回文串。如果指针 i  和指针 j 不指向数字字母，则往前移动直到数字或者字母（可以设个函数判断）
 int  distance = 'a' - 'A' ， 可以用于判断大小写。 
ASCII码中顺序为  数字、大写字母、小写字母。 中间有其他的字符
但是注意 大写P和0 之间，也是差了32，可能会引起某些例子过不了。
*/



class Solution {
    public boolean isPalindrome(String s) {
        if(s.length() < 2 || s == null) return true;
        int left = 0;
        int right = s.length() - 1;
        while(left < right){
            if(valid(s.charAt(left))){
                if(valid(s.charAt(right))){
                    if(judge(s.charAt(left) , s.charAt(right))){
                        left++;
                        right--;
                    }
                    else  return false;
                }
                else{
                    right --;
                }
            }
            else{
                left ++;
            }
        }
        return true;
    }  
    public boolean valid(char c){       // 判断是否是有效的字符
        if(c <= 'Z' && c >= 'A')  return true;
        if(c <= 'z' && c >= 'a')  return true;
        if(c <= '9' && c >= '0')  return true;
        return false;
    }
    public boolean judge(char c1 , char c2){   // 判断两个字符是否不考虑大小写的情况下相等
        if(c1 == c2) return true;
        if(Math.abs(c1 - c2) == 32){
            if(((c1 <= 'Z' && c1 >= 'A') || (c1 <='z' && c1 >= 'a')) && ((c2 <= 'Z' && c2 >='A') || (c2 <= 'z' && c2 >= 'a')) ){
                return true;
            }
        } 
        return false;
    }
}