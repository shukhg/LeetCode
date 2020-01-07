/*
从后往前遍历，遇到一个单词就把它加到结果中。记得忽略首尾和中间的空格
*/



class Solution {
    public String reverseWords(String s) {
        StringBuffer res = new StringBuffer();
        s = s.trim();    
        int i = s.length() - 1, j = s.length();
        while (i > 0) {
            if (s.charAt(i) == ' ') {
                res.append(s.substring(i + 1, j));
                res.append(' ');
                while (s.charAt(i) == ' ') i--; // 忽略单词间的空格
                j = i + 1;
            }
            i--;
        }
        return res.append(s.substring(0, j)).toString();
    }
}
