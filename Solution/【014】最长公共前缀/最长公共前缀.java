/*
每次都是用两个字符串中最短的字符串去匹配另一个字符串
最后返回的是更新后的最短的字符串
*/


class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0 || strs[0].length() == 0)
            return "";
        String short_Str = strs[0];
        String long_Str ;
        for(int i = 1 ; i < strs.length ; i ++){
            long_Str = strs[i];
            if(long_Str.length() < short_Str.length()){
                String temp = short_Str;
                short_Str = long_Str;
                long_Str = temp;
            }
            while( long_Str.indexOf(short_Str) != 0 ){
                short_Str = short_Str.substring(0 , short_Str.length() - 1);
            }
        }
        return short_Str;
    }
}