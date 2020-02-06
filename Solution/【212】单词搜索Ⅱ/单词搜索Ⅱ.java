/*
（1）因为在 dfs 二维矩阵的时候，要判断当前得到的单词是否在单词列表中出现，最简单的可以每次查找 set，但是每走一步都查表效率太低
（2）可以借助字典树，先将单词列表中的单词都加入字典树中，方面后续快速查找
（3）dfs 的过程中每走到一个新的位置，就判断 当前字符 是否能够在字典树的当前结点（随着dfs的过程，后面就不是根结点了）找到 当前字符 对应的 子树
（4）如果可以找到对应的子树，就更新当前结点，往子树走。同时判断当前子树是否是单词，如果是单词则加入到结果中，但是不要返回，因为可能会有 "ad" "add" "addd" 这样的情况
*/




public class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        wordTrie myTrie = new wordTrie();
        trieNode root = myTrie.root;
        for(String s: words)    // 将单词列表中每个单词插入到字典树中
            myTrie.insert(s);
        Set<String> result = new HashSet<>();   //使用set防止重复
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        //遍历整个二维数组
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board [0].length; j++){
                find(board, visited, i, j, m, n, result, root);
            }
        }
        return new LinkedList<String>(result);
    }
    private void find(char[][] board, boolean[][] visited, int i, int j, int m, int n, Set<String> result, trieNode cur){
        if(i < 0 || i >= m || j < 0 || j >= n || visited[i][j])   //边界以及是否已经访问判断
            return;
        cur = cur.child[board[i][j] - 'a'];   // 往子结点走
        visited[i][j] = true;
        if(cur == null){   //如果单词不匹配，回退
            visited[i][j] = false;
            return;
        }
        if(cur.isLeaf){   //找到单词加入
            result.add(cur.val);
            //找到单词后不能回退，因为可能是“ad” “addd”这样的单词得继续回溯
//            visited[i][j]=false;
//            return;
        }
        find(board, visited, i + 1, j, m, n, result, cur);
        find(board, visited, i, j + 1, m, n, result, cur);
        find(board, visited, i, j - 1, m, n, result, cur);
        find(board, visited, i - 1, j, m, n, result, cur);
        //最后要回退，因为下一个起点可能会用到上一个起点的字符
        visited[i][j] = false;
    }
}

//字典树
class wordTrie{
    public trieNode root=new trieNode();
    public void insert(String s){
        trieNode cur = root;
        for(char c: s.toCharArray()){
            if(cur.child[c-'a'] == null){
                cur.child[c-'a'] = new trieNode();
                cur = cur.child[c-'a'];
            }
            else
                cur=cur.child [c-'a'];
        }
        cur.isLeaf = true;
        cur.val = s;
    }
}
//字典树结点
class trieNode{
    public String val;    // 存储的是单词 
    public trieNode[] child = new trieNode[26];
    public boolean isLeaf = false;

    trieNode(){
    }
}
