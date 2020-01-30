/*
（1）初始化一个 result 数组用来保存结果
（2）第一趟遍历，将每个位置左边的数的乘积放到结果数组中，边界按照 1 处理，这也符合逻辑
（3）第二趟从右往左遍历，利用 k 保存某个位置右边数组的乘积，左边乘积 * 右边乘积 即可得到结果
*/


class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int k = 1;
        for(int i = 0; i < nums.length; i ++){   
            result[i] = k;     // 此时 k 存储的是 左边 的数的乘积
            k = k * nums[i];   
        }
        k = 1;
        for(int i = nums.length - 1; i >= 0; i --){
            result[i] = result[i] * k;    // 此时 k 存储的是 右边 的数的乘积
            k = k * nums[i];     
        }
        return result;
    }
}