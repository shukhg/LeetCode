/*
与上题的区别是，此题只能用 数组 去模拟哈希表。
数组中有重复的时候，还是一样的套路，先排序然后找剪枝条件。在此题，剪枝条件
i > 0 && is_used[i - 1] && nums[i - 1] == nums[i]
此处 is_used[i - 1] 比较有意思，在有两个数相同的情况下，如果为 true 则选中的结果为 (后 前)，如果为 false 则选中的结果为（前 后），这两个都是可以的，但是不能不考虑这个。
*/


// 经典回溯
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0)    return result;
        boolean[] is_used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(result, nums, new ArrayList<Integer>(), is_used);
        return result;
    }
    public void dfs(List<List<Integer>> result, int[] nums, List<Integer> temp, boolean[] is_used){
        if(temp.size() == nums.length){
            result.add(new ArrayList<Integer>(temp));
            return ;
        }
        for(int i = 0; i < nums.length; i ++){
            if(is_used[i])  continue;
            if(i > 0 && is_used[i - 1] && nums[i - 1] == nums[i]) continue;  // 这里一定要限定 i - 1 被使用 或者 没有被使用
            temp.add(nums[i]);
            is_used[i] = true;
            dfs(result, nums, temp, is_used);
            temp.remove(temp.size() - 1);
            is_used[i] = false;
        }
    }
}


public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums==null || nums.length==0) { 
            return ans; 
        }
        permute(ans, nums, 0);
        return ans;
    } 
    public void permute(List<List<Integer>> ans, int[] nums, int index) {
        if (index == nums.length) { 
            List<Integer> temp = new ArrayList<>();
            for (int num: nums) 
            { 
                temp.add(num); 
            }
            ans.add(temp);
        }
        Set<Integer> appeared = new HashSet<>();  //此题和上题不一样的地方
        for (int i=index; i<nums.length; ++i) {
            if (appeared.contains(nums[i]) == false) {        // 提高效率的关键
                appeared.add(nums[i]);
                swap(nums, index, i);
                permute(ans, nums, index+1);
                swap(nums, index, i);
            }
        }
    }
    public void swap(int[] nums, int i, int j) {
        int save = nums[i];
        nums[i] = nums[j];
        nums[j] = save;
    }
}
