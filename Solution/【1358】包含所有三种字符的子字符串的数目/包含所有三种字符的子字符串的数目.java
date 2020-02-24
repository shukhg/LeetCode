/*
（1）看到这种题目，子字符串满足一定要求的，就应该去想到滑动窗口
（2）用一个数组对 abc 的出现次数进行计数
（3）维护两个指针i,j，一个代表子串开始位置，一个代表子串结束位置（左闭右开）
（4）如果 s[i:j] 中包含了a 和 b 和 c。则代表s[i:j], s[i:j+1], ...., s[i:]都是可行子串。 总数为 len(s)-j+1 个
（5）如果不包含，代表还没找到以i开始的有效子串， j++ 继续找。直到j到头
（6）如果j到头，代表一个更长的序列也找不到同时包含abc。则更短的序列也不可能，i不必递加，直接输出结果
*/



class Solution {
    public int numberOfSubstrings(String s) {
        int result = 0;
        int[] abc = new int[3];
        for(int j = 0, i = 0; j < s.length();j ++){
            char ch = s.charAt(j);
            abc[ch-'a']++;
            while(abc[0] > 0 && abc[1] > 0 && abc[2] > 0){
                ch = s.charAt(i);
                result = result + s.length() - j;
                abc[ch-'a'] --;
                i++;
            }
        }
        return result;
    }
}