/*
其实很简单，加入短的字符串长度为 k 
然后从 0 开始，逐个去用 substr().equals() 去匹配即可
时间复杂度应该是 O(n²)
如果不能用 substr()那就用 kmp算法，太为复杂了。
*/


class Solution {
    public int strStr(String haystack, String needle) {
        if(needle == null || needle.length() == 0)
            return 0;
        if(haystack == null || haystack.length() == 0)
            return -1;
        int i = 0;
        int k = needle.length();
        while( i + k <= haystack.length()){
            if(haystack.substring(i , i + k).equals(needle)){
                return i;
            }
            else{
                i ++;
            }
        }
        return -1;
    }
}