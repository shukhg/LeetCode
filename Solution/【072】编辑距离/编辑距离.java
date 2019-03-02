/*
（1）利用动态规划，状态转移方程是   dp[i][j] = Math.min( dp[i - 1 ][j - 1]  , Math.min( dp[ i - 1][j] , dp[i][j - 1])) + 1;
（2）要想清楚，此处 dp数组的大小是 length + 1 。因为截取单词的部分长度为0 可以，长度为 length 也可以，所以数组长度是 length+1
*/



class Solution {
    public int minDistance(String word1, String word2) {
        int length_1 = word1.length();
        int length_2 = word2.length();
        if(length_1 == 0){
            return length_2;
        }
        if(length_2 == 0)
            return length_1;
        int[][] dp = new int[length_1 + 1][length_2 + 1];  // 表示word1从0 到 i子串 与 word2从0 到 j子串 的编辑距离
        for(int i = 0 ; i <= length_1 ; i ++){
            dp[i][0] = i;
        }
        for(int i = 0 ; i <= length_2 ; i ++){
            dp[0][i] = i;
        }
        for(int i = 1 ; i <= length_1 ; i ++){
            for(int j = 1 ; j <= length_2 ; j ++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){  // 注意这里是 i - 1，要从第一个字符开始
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else{
                    dp[i][j] = Math.min(dp[i - 1][j - 1] , Math.min(dp[i - 1][j] , dp[i][j - 1]) ) + 1;
                }
            }
        }
        return dp[length_1][length_2];
    }
}