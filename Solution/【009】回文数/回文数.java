/*
此题思路很简单，就可以按照之前的反转数字的方法
如果反转得到的数字相同，那么就算对称的
*/

class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        else if(x < 10){
            return true;
        }
        double temp = x;
        double reverse = 0;
        while(x != 0){
            reverse = reverse * 10 + x % 10;
            x = x / 10;
        }
        if(temp == reverse){
            return true;
        }
        else{
            return false;
        }
    }
}