/*
（1）注意到题中说了 nums[-1] = nums[n] = -∞，所以只要数组中存在一个元素比任一相邻元素大，那么沿着它一定可以找到一个峰值
（2）如果 mid < mid + 1，则说明应该沿着 mid + 1 的方向，所以 left = mid + 1（可能为 mid + 1，但是不可能为 mid）
（3）如果 mid > mid + 1，则说明应该沿着 mid 的方向，所以 right = mid（可能为 mid，但是不可能为 mid + 1）
（4）二分查找不全部都是 left <= right，可以想 case 来判断是否有 = 和返回值
*/


class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < nums[mid + 1]){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        return left;
    }
}