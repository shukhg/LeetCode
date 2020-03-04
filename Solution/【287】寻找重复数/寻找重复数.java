/*
（1）因为给定了一个范围，那么可以用二分法
（2）每次统计 <= mid 的元素个数，如果 count > mid，则说明在 mid 左半部分（包含 mid）
（3）如果 count <= mid，则说明一定在 mid 右半部分（不包含 mid）
*/



class Solution {
    public int findDuplicate(int[] nums) {
        int left = 1, right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            int count = 0;
            for(int num: nums){
                if(num <= mid){   // 要考虑  ==
                    count ++;
                }
            }
            if(count > mid){
                right = mid;   // 可以包含 mid
            }
            else{
                left = mid + 1;  // 一定不含 mid
            }
        }
        return left;
    }
}