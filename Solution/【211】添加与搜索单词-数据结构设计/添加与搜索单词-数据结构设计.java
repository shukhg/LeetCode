/*
（1）使用前缀树实现
（2）插入单词的操作与前缀树一样
（3）搜索单词的时候，区分 '.' 与 其他
（4）如果遇到 '.'，则遍历当前结点的全部子结点，然后在取 '.' 后的字串 和 当前结点的子结点 开始判断，所有需要一个 searchHelp 来传入 node
（5）如果遇到 '.' 的时候循环遍历全部子结点都没返回 true，则返回 false
*/




class WordDictionary {
    public boolean isEnd;
    public char curChar;
    public WordDictionary[] son = new WordDictionary[26];
    /** Initialize your data structure here. */
    public WordDictionary() {
        this.isEnd = false;
        this.curChar = ' ';
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        char[] array = word.toCharArray();
        WordDictionary node = this;
        for(int i = 0; i < array.length; i ++){
            if(node.son[array[i] - 'a'] == null){
                node.son[array[i] - 'a'] = new WordDictionary();
            }
            node = node.son[array[i] - 'a'];
            node.curChar = array[i];
            if(i == array.length - 1){
                node.isEnd = true;
            }
        }
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        WordDictionary root = this;
        return searchHelp(word, root);
    }
    public boolean searchHelp(String word, WordDictionary node) {
        char[] array = word.toCharArray();
        for(int i = 0; i < array.length; i ++){
            if(array[i] != '.'){
                if(node.son[array[i] - 'a'] != null){
                    node = node.son[array[i] - 'a'];   // 因为 root 结点没有存储字符
                }
                else{
                    return false;
                }
            }
            else{
                for(int j = 0; j < 26; j++){   // 遇到 . 的时候遍历每个子树
                    if(node.son[j] != null){
                        if(searchHelp(word.substring(i + 1), node.son[j])){  // 从 . 的下一个字符 和 下一个结点开始
                            return true;
                        }
                    }
                }
                return false;   // 如果在所有的子树都没找到匹配的，就返回 false
            }
        }
        return node.isEnd == true ? true: false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */