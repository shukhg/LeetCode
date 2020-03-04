/*
（1）位运算异或的性质：两个相同数字异或 = 0，一个数和 0 异或还是它本身
（2）将数组内全部的数进行异或，得到的结果就是两个只出现一次的数异或的结果
（3）在这个结果中找到第一个为 1 的位
（4）根据这个为 1 的位可以将数组分为两个部分，这两个只出现一次的数 分别在两边
（5）再对这两边的元素进行异或就可以得到结果
*/




class Solution {
    public int[] singleNumber(int[] nums) {
        if(nums == null || nums.length == 0)    return new int[2];;
        int temp = 0;
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i ++){
            temp = temp ^ nums[i];
        }
        int cur = 1;
        while((temp & cur) == 0){
            cur = cur << 1;
        }
        for(int i = 0; i < nums.length; i ++){
            if((nums[i] & cur) == 0){
                result[0] = result[0] ^ nums[i];
            }
            else{
                result[1] = result[1] ^ nums[i];
            }
        }
        return result;
    }
}