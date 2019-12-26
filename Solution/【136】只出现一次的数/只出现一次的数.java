/*
任何数异或0值不变，任何数与自己异或值为0
因此一个数两次异或同一个数，值不变。而且异或是有交换律的，
比如 a ^ b ^ c ^ a ^ c  = a ^ a ^ c ^ c ^ b = b  
*/




class Solution {
    public int singleNumber(int[] nums) {
        for(int i = 1; i < nums.length; i ++){
            nums[0] = nums[0] ^ nums[i];
        }
        return nums[0];
    }
}