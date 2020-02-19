/*
简单的 dfs，统计当前 list 有多少数字 和 数字和即可
*/



class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(k, n, 0, 1, new LinkedList<>());
        return ans;
    }
    public void dfs(int k, int n, int sum, int begin, LinkedList<Integer> list) {
        if(k == 0) {
            if(n == sum)
                ans.add(new ArrayList<>(list));
            return;
        }
        for(int i = begin; i < 10; i++) {
            list.add(i);
            dfs(k - 1, n, sum + i ,i + 1, list);
            list.removeLast();
        }
    }
}