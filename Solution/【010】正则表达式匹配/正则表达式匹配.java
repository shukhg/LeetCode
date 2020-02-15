/*
（1）dp[i][j] 表示 s 的前 i 个是否能被 p 的前 j 个匹配
（2）如果 p[j] == s[i] 或者 p[j] == '.', 则 dp[i][j] = dp[i-1][j-1]
（3）如果 p[j] == '*', 则要看 p[j - 1]：
        如果 p[j-1] != s[i]，表明 '*' 匹配前面的元素 0 次，这两个元素都要丢弃，也就是 dp[i][j] = dp[i][j-2]
        p[j-1] == s[i] or p[j-1] == "."，表明 '*' 前面的字符能匹配 s[i]，则分为：'*' 匹配 0次、1次、多次
        也就是 dp[i][j] = dp[i-1][j] // 多个字符匹配的情况	
            or dp[i][j] = dp[i][j-1] // 单个字符匹配的情况
            or dp[i][j] = dp[i][j-2] // 没有匹配的情况
*/


class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
                return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;      // dp[i][j] 表示 s 的前 i 个是否能被 p 的前 j 个匹配
        for (int i = 0; i < p.length(); i++) { 
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true; 
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {  // 如果是任意元素 或者是对于元素匹配
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {  // 如果前一个元素不匹配 且不为任意元素
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } 
                    else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                        /*
                        dp[i][j] = dp[i-1][j] // 多个字符匹配的情况	
                        or dp[i][j] = dp[i][j-1] // 单个字符匹配的情况
                        or dp[i][j] = dp[i][j-2] // 没有匹配的情况
                            */
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}