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



// 使用 split
class Solution {
    public String reverseWords(String s) {
        StringBuilder ans = new StringBuilder();
        //去掉s的首尾空格 然后将字符串拆分
        String[] s1 = s.trim().split(" ");
        for(int i = s1.length - 1; i >= 0; i--){
            //空格后面的空格会变成空字符串
            if(!s1[i].equals("")) ans.append(s1[i] + " ");
        }
        //去掉最后添加上的空格
        ans = new StringBuilder(ans.toString().trim());
        return ans.toString();
    }
}
