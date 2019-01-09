/*
可以理解为深度优先搜索，但是要加限定条件。
此处加上了对 left >right 的判断
剩余左括号数目大于剩余右括号数目的时候表明出错了，不能再往这条路走了。
如果 left > 0 就可以继续加入左括号
如果 right > 0 就可以继续加入右括号
 */



class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if(n == 0)
            return result;
        String str = "";
        dfs(result , str , n , n );
        return result;
    }
    public void dfs(List<String> result , String str , int left , int right){
        if(left == 0 && right == 0){
            result.add(str);
            return ;
        }
        if(left > right)
            return ;
        if(left > 0){
            dfs(result , str + "(" , left - 1 , right);
        }
        if(right > 0){
            dfs(result , str + ")" , left , right - 1);
        }
    }
}