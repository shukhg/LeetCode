/*
一直递归的除以 2/3/5 即可
*/


public class Solution {
    public boolean isUgly(int num) {
        while(num >= 5 && num % 5 == 0)
            num = num / 5;
        while(num >= 3 && num % 3 == 0)
            num = num / 3;
        while(num >= 2 && num % 2 == 0)
            num = num / 2;
            
        if(num == 1)
            return true;
        else
            return false;
    }
}