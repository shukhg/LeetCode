/*
显然是回溯算法，但是有个问题，怎么样实现回溯的时候不添加元素呢，可以在 for 循环之前直接先 add temp
想清楚为什么这里不需要 for循环 如果有 for 循环会出现很多重复的。
当然，先排序然后用HashSet 调用 for循环也是可以的 
*/


class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0)    return result;
        HashSet<Integer> hashset = new HashSet<>();
        dfs(result, nums, 0, new ArrayList<Integer>());
        return result;
    }
    public void dfs(List<List<Integer>> result, int[] nums, int start, List<Integer> temp){
        result.add(new ArrayList<Integer>(temp));
        for(int i = start; i < nums.length; i ++){
            temp.add(nums[i]);
            dfs(result, nums, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }
}



// 借助 sort 和 hashset
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
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