/*
典型的 dfs ，建议用一个元素的特殊情况想清楚过程
*/


class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return new ArrayList<>();
        HashSet<List<Integer>> result = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        dfs(result , nums , target , list , 0);
        return new ArrayList<>(result);
    }
    public void dfs(HashSet<List<Integer>> result , int[] nums , int target , List<Integer> list , int start){
        if(target < 0)    // 要注意此处，不然会无线递归
            return ;
        if(target == 0){
            result.add(new ArrayList<Integer>(list));
            return ;
        }
        for(int i = start ; i < nums.length ; i ++){
            list.add(nums[i]);
            dfs(result , nums , target - nums[i] , list , i);
            list.remove(list.size() - 1);
        }
    }
}




class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null || candidates.length == 0){
            return result;
        }
        Arrays.sort(candidates);
        List<Integer> temp = new ArrayList<>();
        dfs(result, candidates, target, temp, 0);
        return result;
    }
    public void dfs(List<List<Integer>> result, int[] candidates, int target, List<Integer> temp, int index){
        if(target < 0){
            return ;
        }
        if(target == 0){
            result.add(new ArrayList<Integer>(temp));
            return ;
        }
        for(int i = index; i < candidates.length; i ++){
            if(candidates[i] > target)  break;
            temp.add(candidates[i]);
            dfs(result, candidates, target - candidates[i], new ArrayList<Integer>(temp), i);
            temp.remove(temp.size() - 1);
        }
    }
}