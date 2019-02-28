/*
简单的 split()方法
*/

class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        if(s == null || s.length() == 0)
            return 0;
        String[] arr = s.split(" ");
        int result = arr[arr.length - 1].length();
        return result;
    }
}