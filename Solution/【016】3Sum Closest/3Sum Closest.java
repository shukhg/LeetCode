/*
注意此处没有之前类似三数之和这样的内层 while 提高效率，因为此处直接返回了
其他的和三数之和差不多
*/


class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return 0;
        int min = Integer.MAX_VALUE;
        int result = 0;
        Arrays.sort(nums);
        for(int i = 0 ; i < nums.length ; i ++){
            int j = i + 1 ;
            int k = nums.length - 1;
            while( j < k ){
                if(Math.abs(nums[i] + nums[j] + nums[k] - target) < min){
                    min = Math.abs(nums[i] + nums[j] + nums[k] - target);
                    result = nums[i] + nums[j] + nums[k] ;
                }
                if(nums[i] + nums[j] + nums[k] == target){   //此处也是和 target的判断
                    return nums[i] + nums[j] + nums[k];
                }
                else if(nums[i] + nums[j] + nums[k] > target){   //有关target的判断
                    k = k - 1;
                }
                else{
                    j = j + 1;
                }
            }
        }
        return result;
    }
}