/* 常规方法很容易想到，用内外两层循环即可
*  更加有效的方法是利用 HashMap存储 nums数组的值-索引 
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return null;
        HashMap<Integer,Integer> hashmap = new HashMap<>();
        int[] result = new int[2];
        for(int i = 0 ; i < nums.length ; i ++){
            int div = target - nums[i];
            if(hashmap.containsKey(div)){
                result[0] = i;
                result[1] = hashmap.get(div);
                return result;
            }
            hashmap.put(nums[i] , i);
        }
        return result;
    }
}