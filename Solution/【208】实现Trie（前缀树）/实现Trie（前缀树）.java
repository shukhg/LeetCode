/*
（1）首先要理解什么是 前缀树。简单来说，是每个结点（除了根结点）都是一个字符的多叉树，在本题中最多 26 个子树
（2）每个结点，有 curChar、isEnd、tries 三个属性，分别代表 当前结点的字符、是否此分支最后一个结点（代表单词结尾）、子树组成的数组
（3）在每个方法中通过 Trie node = this 获得当前的对象
（4）插入：遍历每个字符，如果不存在某个子树就生成该子树，然后更新 node 使其往下走，对 node.curChar 和 node.isEnd 赋值
（5）搜索：遍历每个字符，如果不存在某个子树则返回 false，最后一个字符的时候判断是否是  isEnd
（6）搜索前缀：同搜索，只是在最后一个字符的时候不用进行判断，而是直接返回 true
*/



class Trie {
    public boolean isEnd;
    public char curChar;
    public Trie[] tries = new Trie[26];  // 存储至多 26 棵子树
    /** Initialize your data structure here. */
    public Trie() {
        this.isEnd = false;
        this.curChar = ' ';
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] array = word.toCharArray();
        Trie node = this;
        for(int i = 0; i < array.length; i ++){
            if(node.tries[array[i] - 'a'] == null){
                node.tries[array[i] - 'a'] = new Trie();
            }
            node = node.tries[array[i] - 'a'];
            node.curChar = array[i];
            if(i == array.length - 1){
                node.isEnd = true;
            }
        }
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] array = word.toCharArray();
        Trie node = this;
        for(int i = 0; i < array.length; i ++){
            if(node.tries[array[i] - 'a'] != null){
                node = node.tries[array[i] - 'a'];   // 因为 root 结点没有存储字符
                if(node.curChar != array[i]){    // 这里其实是可以删去的，没有这个 if 和 return 也是可以过的，因为上面的 array[i] - 'a' 就对此进行实现了
                    return false;
                }
            }
            else{
                return false;
            }
        }
        return node.isEnd == true ? true: false;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] array = prefix.toCharArray();
        Trie node = this;
        for(int i = 0; i < array.length; i ++){
            if(node.tries[array[i] - 'a'] != null){
                node = node.tries[array[i] - 'a'];   // 因为 root 结点没有存储字符
                if(node.curChar != array[i]){    // 这里其实是可以删去的，没有这个 if 和 return 也是可以过的，因为上面的 array[i] - 'a' 就对此进行实现了
                    return false;
                }
            }
            else{
                return false;
            }
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
