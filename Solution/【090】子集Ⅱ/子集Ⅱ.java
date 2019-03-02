/*
记住只有子集和子集Ⅱ 不需要用 for 循环

当然，也可以借助 sort 和 HashSet，这样就要 for 循环了
*/


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
