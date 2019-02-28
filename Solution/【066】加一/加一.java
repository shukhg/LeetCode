/*
从右边往左边判断，如果是9 就设置为0
否则就自增1
如果全部都为9 ，就创建一个新的数组
*/



class Solution {
    public int[] plusOne(int[] digits) {
        if(digits == null || digits.length == 0)
            return digits;
        for(int i = digits.length - 1 ; i >= 0 ; i --){
            if(digits[i] == 9){
                digits[i] = 0;
            }
            else{
                digits[i] = digits[i] + 1;
                return digits;
            }
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }
}