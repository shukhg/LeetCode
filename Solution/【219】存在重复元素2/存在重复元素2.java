/*
直接用 HashMap 存储即可
*/



class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return false;
        if(nums.length == 1) 
            return false;
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++){
            if(hashmap.containsKey(nums[i]) == true){
                int index = hashmap.get(nums[i]);
                if(Math.abs(index - i) <= k){
                    return true;
                }
                else{
                    hashmap.put(nums[i] , i);
                }
            }
            else{
                hashmap.put(nums[i] , i);
            }
        }
        return false;
    }
}