/* 常规方法很容易想到，用内外两层循环即可
*  更加有效的方法是利用 HashMap存储 nums数组的值-索引 
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer , Integer> hashmap = new HashMap<>();
        int[] ans = new int[2];
        for(int j = 0 ; j < nums.length ; j++){
            hashmap.put(nums[j] , j);
        }
        for(int i = 0 ; i < nums.length ; i++){
            int temp = target - nums[i];
            if(hashmap.containsKey(temp) == true  && hashmap.get(temp) != i ){
                ans[0] = hashmap.get(temp);
                ans[1] = i;
            }
            
        }
        return ans; 
    }
}