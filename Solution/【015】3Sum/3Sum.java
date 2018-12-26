/*
类似于两个数求和，先固定一个指针，然后再设置两个指针一起往中间移动
先排序，利用 Set去除重复，并且利用内层的 while提高效率 
最后利用 Set生成 List
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0)
            return result;
        Arrays.sort(nums);   // 排序配合内层while提高效率
        HashSet<List<Integer>> hashset = new HashSet<>();
        for(int i = 0 ; i < nums.length ; i ++ ){
            int j = i + 1;
            int k = nums.length - 1;
            while( j < k ){
                if(nums[i] + nums[j] + nums[k] == 0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    hashset.add(list);
                    while(j < k && nums[j] == nums[j + 1]){
                        j ++;
                    }
                    while( j < k && nums[k] == nums[k - 1]){
                        k --;
                    }
                    j ++;
                    k --;
                }
                else if(nums[i] + nums[j] + nums[k] < 0){
                    j ++;
                }
                else{
                    k --;
                }
            }
        }
        return new ArrayList<List<Integer>>(hashset);   // set生成list
    }
}