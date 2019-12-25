/*
（1）基于之前的找到最长回文子串的方法，利用 二维 dp 存储一个字串是否是回文串
（2）利用 min_s 存储从 0 位置 到 i 位置的子串的最小分割次数，初始化都是 i，因为可以分割成一个个单字符
（3）j 是子串起始位置，如果 j == 0 则表明此子串整个都为回文串，所有 min_s[i] = 0，否则 为 原先分割方法次数 与 0 到 j - 1 的分割方法次数 + 1（从 j 到 i 所以需要 +1 ）
*/




class Solution {
    public int minCut(String s) {
        int len = s.length();
        int[] min_s = new int[len];
        boolean[][] dp = new boolean[len][len];
        for(int i = 0; i < len; i ++){
            min_s[i] = i;
            for(int j = 0; j <= i; j ++){
                if(s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j + 1][i - 1])){
                    dp[j][i] = true;
                    if(j == 0){
                        min_s[i] = 0;
                    }
                    else{
                        min_s[i] = Math.min(min_s[i], min_s[j - 1] + 1);
                    }
                }
            }
        }
        return min_s[len - 1];
    }
}


