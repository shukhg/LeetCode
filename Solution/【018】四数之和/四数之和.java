/*
类似于三数之和，也就再多了一层循环而已。
注意用最内层 while 和 Arrays.sort()加快时间
*/


class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        if(nums == null || nums.length == 0)
            return result;
        HashSet<List<Integer>> hashset = new HashSet<>();
        for(int i = 0 ; i < nums.length ; i ++){
            for(int j = i + 1 ; j < nums.length ; j ++){
                int k = j + 1;
                int m = nums.length - 1;
                while( k < m ){
                    if(nums[i] + nums[j] + nums[k] + nums[m] == target ){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.add(nums[m]);
                        hashset.add(list);
                        while( k < m && nums[k] == nums[k + 1])
                            k ++;
                        while( k < m && nums[m] == nums[m - 1])
                            m --;
                        k ++;
                        m --;
                    }
                    else if(nums[i] + nums[j] + nums[k] + nums[m] < target )
                        k ++;
                    else
                        m --;
                }
            }
        }
        return new ArrayList<List<Integer>>(hashset);
    }
}