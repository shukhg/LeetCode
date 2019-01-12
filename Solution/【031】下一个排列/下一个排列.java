/*
从后往前走，如果一直是递增，则将整个数组逆置。
如果找到最开始的一个非递增的元素，并且记录位置index
并且在此元素后面的元素中找到一个大于该元素的最小元素
将这两个元素交换，然后对 index 后面的元素逆置就得到了结果。
例如：1,5,8,6,4,3        index = 1   交换5 和 6 就得 1,6,8,5,4,3
再逆置 index后面的元素 1,6,3,4,5,8 即得到正确结果
*/



class Solution {
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length <= 1)
            return ;
        int count = 1;
        for(int i = nums.length - 1 ; i > 0 ; i--){
            if(nums[i] <= nums[i - 1]){
                count ++;
            }
            else{
                int index = i - 1;          // index是从后往前不满足递增的第一个元素
                int k = nums.length - 1 ;   // k 用来记录比 index对应元素更大的最小元素的索引
                int min = Integer.MAX_VALUE;     // 上行要找到最小的
                for(int j = nums.length - 1 ; j > index ; j --){ 
                    if( nums[j] < min && nums[j] > nums[index]  ){
                        k = j ;
                        min = nums[j];
                    }
                }
                swap(nums , index , k);     // 交换 index 和 k 的元素
                reverse(nums , index + 1 , nums.length - 1);   // 逆置 index 后面的全部元素
                return ;
            }
        }
        if(count == nums.length){
            reverse(nums , 0 , nums.length - 1);
            return ;
        }
    }
    public void swap(int[] nums , int left , int right){   // 交换两个元素 
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
    public void reverse(int[] nums , int left , int right){   // 逆置一段数组
        while(left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left ++;
            right --;
        }
    }
}