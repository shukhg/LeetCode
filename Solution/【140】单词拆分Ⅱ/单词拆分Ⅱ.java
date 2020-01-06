/*
（1）将 wordDict 改为 set 降低时间复杂度
（2）先用上一题的 dp，得到从开始到 index 的前缀是否可以拆分
（3）典型的回溯，不过既然 dp 是从开始到 index 的前缀是否可以拆分，那么 dfs 中的 for 循环要判断 后缀是否在 wordSet 中
    如果在 wordSet 中，则更新 end，然后再往前走
*/




public class Solution{
    public List<String> wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        Set<String> wordSet = new HashSet<>(wordDict);
        // 生成 dp 数组来表示前 index 个字符是否可以拆分
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

        List<String> res = new ArrayList<>();
        if (dp[len]) {   // 如果可以拆分
            LinkedList<String> queue = new LinkedList<>();
            dfs(s, len, wordSet, res, queue, dp);
            return res;
        }
        return res;
    }

    public void dfs(String s, int end, Set<String> wordSet, List<String> res, LinkedList<String> queue, boolean[] dp) {
        if (end == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String word : queue) {
                stringBuilder.append(word);
                stringBuilder.append(" ");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);  // 删除最后一个 " "
            res.add(stringBuilder.toString());
            return;
        }

        for (int i = end - 1; i >= 0; i--) {
            if (dp[i]) {
                String suffix = s.substring(i, end);  // 取后缀
                if (wordSet.contains(suffix)) {
                    queue.addFirst(suffix);
                    dfs(s, i, wordSet, res, queue, dp);
                    queue.removeFirst();
                }
            }
        }
    }
}


