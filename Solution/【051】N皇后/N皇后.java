/*
记录 行、列、正对角线、负对角线 不能有 >= 2 个棋子
（1）不需要记录 行，记录一个 列即可。因为遍历的过程中是每行生成一个字符串，所以结果肯定是不同行的
（2）正对角线上的元素 相加之和 相同
（3）负对角线上的元素 相减之差 相同
（4）综合起来，也就是  (!col.contains(j) && !z_diagonal.contains(i + j) && !f_diagonal.contains(i - j))
*/




public class Solution {
    public List<List<String>> solveNQueens(int n) {
        Set<Integer> col = new HashSet<>();
        Set<Integer> z_diagonal = new HashSet<>();
        Set<Integer> f_diagonal = new HashSet<>();
        List<List<String>> res = new ArrayList<>();
        backtrack(0, n, res, new ArrayList<String>(), col, z_diagonal, f_diagonal);
        return res;
    }

    public void backtrack(int i, int n, List<List<String>> res, ArrayList<String> tmp, Set<Integer> col, Set<Integer> z_diagonal, Set<Integer> f_diagonal) {
        if (i == n) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int j = 0; j < n; j++) {
            if (!col.contains(j) && !z_diagonal.contains(i + j) && !f_diagonal.contains(i - j)) {
                col.add(j);
                z_diagonal.add(i + j);
                f_diagonal.add(i - j);
                char[] s = new char[n];    
                Arrays.fill(s, '.');     
                s[j] = 'Q';
                tmp.add(new String(s));
                backtrack(i+1,n,res,tmp,col,z_diagonal,f_diagonal);
                tmp.remove(tmp.size() - 1);
                col.remove(j);
                z_diagonal.remove(i + j);
                f_diagonal.remove(i - j);
            }
        }
    }
}



