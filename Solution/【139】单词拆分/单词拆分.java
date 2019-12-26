/*
（1）此题用二维 dp 数组完全没有意义，比如 dp[0][len] 是需要初始化的，但是这个不就是结果吗？
（2）dp 数组表示从 0 到 i 是否可以拆分为单词，所以外层循环是实现这个功能
（3）内层循环的目的是为了判断 0-i 是否可以拆分，这里需要遍历 0 到 i，一旦找到一个就 break
*/

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0)    return false;
        if(wordDict == null || wordDict.isEmpty())  return false;
        boolean[] dp = new boolean[s.length() + 1];
        HashSet<String> wordSet = new HashSet<>(wordDict);
        dp[0] = true;
        for(int i = 1; i < s.length() + 1; i ++){
            for(int j = 0; j < i; j ++){
                if(dp[j] == true){
                    String substr = s.substring(j, i);
                    if(wordSet.contains(substr)){
                        dp[i] = true;
                        break;    // 这里 break 加快速度
                    }
                }
            }
        }
        return dp[s.length()];
    }
}