/*
最开始我在思考，如果 s = "abcdefgh" ， wordDict = {"abc" , "abcd" , "edgh"}的情况
这种情况下 dp[3] = true , dp[4] = true , dp[8]的判断依赖于 dp[4] ，但是会是 false
一般两层循环的话，dp数组都是 二维数组，当然此题可以用二维数组解决这个问题
此处用的一维数组但是也是两层循环，就是为了解决我最开始的这个问题
只要内层循环遍历过程中有一个满足条件，就 break 循环
所以 dp[8]的判断不止是依赖于 dp[4]，还会依赖于 dp[3]
只有是用的一维数组才是仅仅依赖于 dp[4]
*/

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0)
            return false;
        if(wordDict == null || wordDict.size() < 1)
            return false;
        boolean[]  dp = new boolean[s.length() + 1 ];
        dp[0] = true;
        for(int i = 1 ; i < s.length() + 1 ; i ++){
            for(int j = 0 ; j < i ; j ++){  // 既然有这个循环，就解决了"ab" "abc" 都在字典中出现并且只依赖"abc"的问题
                if(dp[j] == true ){
                    String sub = s.substring( j , i );
                    if(wordDict.contains(sub)){
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[s.length()];
    }
}