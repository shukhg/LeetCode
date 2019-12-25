/*
（1）将 wordList 改为 set，减少查找时间
（2）使用两个 set 一起往中间走，实现双端 bfs。如果 set1 比 set2 大，尽量交换使得 set1 < set2
（3）遍历 set1 中每个单词，然后对每个单词中的每个字符都用 a 到 z 替换，如果在 s2 中找到就返回结果，如果在 wordSet 中没有就 continue   如果将新得到的单词添加到了 set 中，就在 wordSet 中移除
（4）注意此处是新建一个 set 然后添加了新的单词后赋给 set1。因为 set1 原始的那些元素肯定不能保留
*/





class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        if(wordSet.contains(endWord) == false)  return 0;
        HashSet<String> s1 = new HashSet<>();
        HashSet<String> s2 = new HashSet<>();
        s1.add(beginWord);
        s2.add(endWord);
        int n = beginWord.length();
        int step = 0;
        while(s1.isEmpty() == false && s2.isEmpty() == false){
            step ++;
            if(s1.size() > s2.size()){
                HashSet<String> temp = s1;
                s1 = s2;
                s2 = temp;
            }
            HashSet<String> s = new HashSet<>();  // 存储已经改变过的单词
            for(String word: s1){
                for(int i = 0; i < n; i ++){   // 尝试对当前 word 每次字符进行遍历替换
                    char[] char_arr = word.toCharArray();    // 记住这里是对每个字符进行替换的时候都要新生成一个数组
                    for(char ch = 'a'; ch <= 'z'; ch ++){
                        char_arr[i] = ch;
                        String temp = String.valueOf(char_arr);
                        if(s2.contains(temp))   return step + 1;
                        if(wordSet.contains(temp) == false) continue;
                        wordSet.remove(temp);  // 
                        s.add(temp);
                    }
                }
            }
            s1 = s;
        }
        return 0;
    }
}

