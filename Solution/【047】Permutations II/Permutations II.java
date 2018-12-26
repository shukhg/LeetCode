/*
此题可以先排序，然后用 Set对每个元素操作去除重复元素，但是这样效率比较低。
要理解为什么会有重复，是因为两个甚至多个相同的元素交换位置，实际上这样是没有效果的。
所以我们可以想办法避免两个相同的元素交换
我们可以在每次循环前简历一个Set，遇到新的不同的元素就加入Set 并且执行交换和搜索，要是已经出现过的就不进行交换和搜索了
*/


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