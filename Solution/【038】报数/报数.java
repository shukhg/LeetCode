/*
注意 say 的时候，最后一个字符也是要考虑的
*/


class Solution {
    public String countAndSay(int n) {
        if(n == 0)
            return "";
        if(n == 1)
            return "1";
        String result = "1";
        for(int i = 1 ; i < n ; i ++){
            result = say(result);
        }
        return result;
    }
    public String say(String str){
        String result = "";
        int count = 1;
        for(int i = 1 ; i < str.length() ; i ++){
            if(str.charAt(i) == str.charAt(i - 1)){
                count ++;
            }
            else{
                result = result + count + str.charAt(i - 1);
                count = 1;
            }
        }
        result = result + count + str.charAt(str.length() - 1);
        return result;
    }
}