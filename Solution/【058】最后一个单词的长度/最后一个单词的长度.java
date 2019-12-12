/*
从后面往前面遍历
*/

class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        if(s == null || s.length() == 0){
            return 0;
        }
        int result = 0;
        int index = 0;
        for(int i = s.length() - 1; i >= 0; i --){
            if(s.charAt(i) == ' '){
                return result;
            }
            else{
                result = result + 1;
            }
        }
        return result;
    }
}