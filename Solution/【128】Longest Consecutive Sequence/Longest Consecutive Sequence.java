/*
既然题目要求O(n)的时间复杂度，所以不能涉及到排序。
可以用一个Set先存储全部的元素，再第二轮循环遍历到某个元素的时候
先把与其连续递增的元素计数并且全部删除，再把与其连续递减的元素计数并且全部删除
如果count大于max就更新max
*/


class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        HashSet<Integer> hashset = new HashSet<>();
        for(int i = 0 ; i < nums.length ; i ++)
            hashset.add( nums[i] );
        int max = 0;
        for(int i = 0 ; i < nums.length ; i ++){
            int count = 0;
            int value = nums[i];
            int temp_value = value;   // 为了存储当前的value
            while( hashset.contains(value) == true ){  // 找到更大的
                count ++ ;
                hashset.remove(value);
                value ++;
            }
            while( hashset.contains(temp_value - 1) == true){   // 找到更小的
                count ++;
                hashset.remove(temp_value);
                temp_value -- ;
            }
            if(count > max )
                max = count;
        }
        return max ;
    }
}