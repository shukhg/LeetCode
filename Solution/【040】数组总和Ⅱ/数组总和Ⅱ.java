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