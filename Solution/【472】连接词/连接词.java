/*
（1）对于每个单词，尝试用 dfs 判断它是否是 连接词
（2）因为该单词身边也在单词字典中，所以需要一个 depth 计数，表明该单词是由字典中的几个单词组成，要满足 depth > 1
（3）dfs 中 for 循环遍历每一个字符，分为 index-i 和 i+1 - len 两个子串。如果左子串可以在字典中找到，而且右子串 dfs 的结果也为 true，则返回 true
*/



class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        HashSet<String> set = new HashSet<>();
        List<String> result = new ArrayList<>();
        for(String s : words)
            set.add(s);
        for(String s : words){    // 对每个单词进行 dfs
            if(dfs(s, set, 0, 0))   // 第一个 0 用于标志此单词可又多少 其他单词组成，第二个 0 表示单词中字符的索引
                result.add(s);
        }
        return result;
    }
    public boolean dfs(String s, Set<String> set, int depth, int index){
        if(index == s.length())   // 判断是否由 1个以上的子串组成  
            return depth > 1;   
        for(int i = index + 1; i <= s.length(); i++){
            if(set.contains(s.substring(index, i))){  // 前半部分子串可以在字典中找到
                //只要有一种能够组合出来，就返回true
                if(dfs(s, set, depth + 1, i)){   //  后半部分字串也可以被表示为多个单词的组合
                    return true;
                }
            }
        }
        return false;
    }
}