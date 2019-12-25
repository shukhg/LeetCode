/*
典型的 dfs
*/


class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null || candidates.length == 0)    return result;
        Arrays.sort(candidates);
        List<Integer> temp = new ArrayList<>();
        dfs(result, candidates, temp, target, 0);
        return result;
    }
    public void dfs(List<List<Integer>> result, int[] candidates, List<Integer> temp, int target, int index){
        if(target == 0){
            result.add(new ArrayList<Integer>(temp));
            return ;
        }
        for(int i = index; i < candidates.length; i ++){
            if(candidates[i] > target)  break;
            temp.add(candidates[i]);
            dfs(result, candidates, temp, target - candidates[i], i);
            temp.remove(temp.size() - 1);
        }
    }
}