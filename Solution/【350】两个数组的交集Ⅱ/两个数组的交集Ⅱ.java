/*
利用 HashMap 遍历第一个数组，value是出现的次数。
遍历第二个数组的时候，要相应更新 HashMap，如果次数等于0 了就 remove
*/


class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null)
            return null;
        List<Integer> list = new ArrayList<>();   // list.toArray()转为结果数组
        HashMap<Integer , Integer> hashmap = new HashMap<>(); // value是出现的次数
        for(int i = 0 ; i < nums1.length ; i ++){
            if(hashmap.containsKey(nums1[i]) == false){
                hashmap.put(nums1[i] , 1);
            }
            else{
                hashmap.put(nums1[i] , hashmap.get(nums1[i]) + 1);
            }
        }
        for(int i = 0 ; i < nums2.length ; i ++){
            if(hashmap.containsKey(nums2[i]) == true ){
                list.add(nums2[i]);
                hashmap.put(nums2[i] , hashmap.get(nums2[i]) - 1);
                if(hashmap.get(nums2[i]) == 0)
                    hashmap.remove(nums2[i]);
            }
        }
        int[] result = new int[list.size()];
        for(int i = 0 ; i < list.size() ; i ++)
            result[i] = list.get(i);
        return result;
    }
}