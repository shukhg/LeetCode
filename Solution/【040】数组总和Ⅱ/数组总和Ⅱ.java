/*
和上题不同之处在于，此处要从 i 的下一个元素开始，这样就不会重复利用相同的元素
*/

class Solution {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return new ArrayList<>();
        HashSet<List<Integer>> result = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        dfs(result , nums , target , list , 0);
        return new ArrayList<>(result);
    }
    public void dfs(HashSet<List<Integer>> result , int[] nums , int target , List<Integer> list , int start){
        if(target < 0)
            return ;
        if(target == 0){
            result.add(new ArrayList<Integer>(list));
            return ;
        }
        for(int i = start  ; i < nums.length ; i ++){
            list.add(nums[i]);
            dfs(result , nums , target - nums[i] , list , i + 1);
            list.remove(list.size() - 1);
        }
    }
}




class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            if(i > index && candidates[i] == candidates[i - 1]) continue;
            temp.add(candidates[i]);
            dfs(result, candidates, target - candidates[i], new ArrayList<Integer>(temp), i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}