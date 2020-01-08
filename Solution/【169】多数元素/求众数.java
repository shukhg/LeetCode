/*
有一种投票算法，如果遇到两个不同的数字，就全部都删去。遍历完整个数组，剩下的就是所求的
不过这种算法限定了 众数的出现次数大于等于数组长度的一半
*/


// 投票算法
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = nums[0], count = 1;
        for(int i = 1; i < nums.length; i ++){
            if(candidate == nums[i]){
                count ++;
            }
            else{
                count --;
                if(count == 0){
                    candidate = nums[i + 1];
                }
            }
        }
        return candidate;
    }
}



// 暴力解法
class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer , Integer> hashmap = new HashMap<>();
        int temp = 0;
        for(int i = 0  ; i < nums.length ; i++){
            if(hashmap.containsKey(nums[i])  == false){
                hashmap.put(nums[i] , 1);
            }
            else{
                temp = hashmap.get(nums[i]) + 1;
                hashmap.replace(nums[i] , temp);
            }
        }
        for(Integer key : hashmap.keySet()){
            if(hashmap.get(key) > nums.length / 2 )  return key;
        }
        return 111;
    }
}
