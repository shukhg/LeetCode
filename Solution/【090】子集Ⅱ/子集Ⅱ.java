/*
同 子集，因为需要在元素被添加到 temp 中就将 temp 添加到 result，所以在 for 循环前就添加到 result 中
同其他题目，for 循环中利用  if( i > index && nums[i] == nums[i - 1])  对元素去重
*/




// 经典的递归
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0)    return result;
        Arrays.sort(nums);
        dfs(result, nums, 0, new ArrayList<Integer>());
        return result;
    }
    public void dfs(List<List<Integer>> result, int[] nums, int index, List<Integer> temp){
        result.add(new ArrayList<Integer>(temp));
        for(int i = index; i < nums.length; i ++){
            if( i > index && nums[i] == nums[i - 1]){
                continue;
            }
            temp.add(nums[i]);
            dfs(result, nums, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }
}









class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);     // 先排序
        HashSet<List<Integer>> hashset = new HashSet<>();   // 利用 Set去除重复
        if(nums == null || nums.length == 0 )
            return result;
        dfs( hashset , nums , new ArrayList<Integer>()  ,   0 );
        return new ArrayList<List<Integer>>(hashset);
    }
    public void dfs(HashSet<List<Integer>> hashset , int[] nums ,  List<Integer> list  , int start ){
        if(start == nums.length){
            hashset.add(list);
            return ;
        }
        dfs( hashset , nums , new ArrayList<Integer>(list) , start + 1 );
        list.add( nums[start] );
        dfs( hashset , nums , new ArrayList<Integer>(list) , start + 1 );
        list.remove( list.size() - 1);
    }
}



// 借助 sort 和 hashset

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        HashSet<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums);
        if(nums == null || nums.length == 0)
            return new ArrayList<List<Integer>>();
        dfs( result , nums , new ArrayList<Integer>() , 0 );
        return new ArrayList<List<Integer>>(result);
        
    }
    public void dfs(HashSet<List<Integer>> result , int[] nums , List<Integer> list , int start ){
        if( start == nums.length ){
            result.add( new ArrayList<>(list));
            return ;
        }
        for(int i = start ; i < nums.length ; i ++){
            dfs(result , nums , new ArrayList<Integer>(list) , i + 1);
            list.add(nums[i]);
            dfs(result , nums , new ArrayList<Integer>(list) , i + 1);
            list.remove(list.size() - 1);
        }

    }
}
