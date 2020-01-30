/*
（1）首先判断两个字符串长度是否相等，不相等则直接返回 false
（2）若相等，则初始化 26 个字母哈希表，遍历字符串 s 和 t
（3）s 负责在对应位置增加，t 负责在对应位置减少
（3）如果哈希表的值都为 0，则二者是字母异位词
*/



class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        int[] hash = new int[26];
        for(int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a'] ++;
            hash[t.charAt(i) - 'a'] --;
        }
        for(int i = 0; i < 26; i++)
            if(hash[i] != 0)
                return false;
        return true;
    }
}