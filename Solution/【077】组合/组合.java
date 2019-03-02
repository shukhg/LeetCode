/*
记住，一般情况回溯中加起来满足某个条件的，都是在 dfs中用 count - * ，比如这里加起来的个数为 k 个，dfs就是调用 k - 1 个
然后，注意 dfs 中的 start，只有全排列 dfs的 start = start + 1 ，其他都是 start = i 或者 start = i + 1
因为全排列的当前元素要与其他元素交换，完成这个循环后再考虑下一个元素
但是其他不是这样，其他是当前元素与下面两个元素进行组合都可能
*/


class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if(n == 0 || k == 0)
            return result;
        List<Integer> list = new ArrayList<>();
        dfs(result , n , k , list , 1 );
        return result;
    }
    public void dfs(List<List<Integer>> result , int n , int k , List<Integer> list , int start){
        if(k == 0){    //  改为 list.size() == k 也是可以的
            result.add(new ArrayList<Integer>(list));
            return ;
        }
        for(int i = start ; i <= n ; i ++){
            list.add(i);
            dfs(result , n , k - 1 , list , i + 1);
            list.remove(list.size() - 1);
        }
    }
}