/*
（1）设置一个 small 和一个 mid，用于保存遍历过程中的最小 和 比最小略大的元素（这个略大不能考虑已经遍历过的，只能在遍历的过程中考虑右边的元素进行更新）
（2）遇到比 small 更小的元素就更新 small，遇到 small 和 mid 之间的元素就更新 mid，都不属于的话就说明找到了递增的三元组
（3）乍一看感觉找到的这个三元组的顺序可能不对，但是实际上去想想 case 其实这样是合理的
*/



class Solution {
    public boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i ++){
            if(nums[i] <= small){   // 这里一定要 <=，不然会把相等的元素给放到下一个 if
                small = nums[i];
            }
            else if(nums[i] <= mid){
                mid = nums[i];
            }
            else{
                return true;
            }
        }
        return false;
    }
}