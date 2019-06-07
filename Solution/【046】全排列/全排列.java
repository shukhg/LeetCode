/*
典型的递归，此处不要辅助的 List 是因为可以遍历数组直接加入 result
记住此处是交换
*/


class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0)
            return result;
        dfs(result , nums , 0);
        return result;
    }
    public void dfs(List<List<Integer>> result , int[] nums , int start){
        if(start == nums.length ){
            List<Integer> list = new ArrayList<>();
            for(int i = 0 ; i < nums.length ; i ++){
                list.add(nums[i]);
            }
            result.add(new ArrayList<Integer>(list));
            return ;
        }
        for(int i = start ; i < nums.length ; i ++){
            swap(nums , start , i);
            dfs(result , nums , start + 1);   // 切记这里是 start + 1 而不是 i + 1
            swap(nums , start , i);
        }
    }
    public void swap(int[] nums , int left , int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}