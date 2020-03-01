/*
（1）先排序，这样可以不用遍历完全部的 pair，尽管时间复杂度仍然是 O(n2)
（2）利用 nums.clone() 实现数组的克隆
*/



class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] sortArray = nums.clone();
        Arrays.sort(sortArray);
        int[] result = new int[nums.length];
        for(int i = 0; i < result.length; i ++) {
            for(int j = 0; j < sortArray.length; j ++) {
                if(nums[i] == sortArray[j]) {
                    result[i]=j;
                    break;
                }
            }
        }
        return result;
    }
}
