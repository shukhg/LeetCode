/*
简单的用 map 和 set
*/


class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> hashmap = new HashMap<>();  // 可以记录出现的次数
        HashSet<Integer> result = new HashSet<>();
        for(int i = 0 ; i < nums1.length ; i ++){
            if(hashmap.containsKey(nums1[i]) == false)
                hashmap.put(nums1[i] , 1);
            else
                hashmap.put(nums1[i] , hashmap.get(nums1[i]) + 1);
        }
        for(int i = 0 ; i < nums2.length ; i ++){
            if(hashmap.containsKey(nums2[i]) == true){
                if(hashmap.get(nums2[i]) == 1){
                    result.add(nums2[i]);
                    hashmap.remove(nums2[i]);
                }
                else{
                    result.add(nums2[i]);
                    hashmap.put(nums2[i] , hashmap.get(nums2[i]) - 1);
                }
            }
        }
        int[] ans = new int[result.size()];
        int count = 0;
        for(Integer num : result ){
            ans[count++] = num;
        }
        return ans;
    }
}