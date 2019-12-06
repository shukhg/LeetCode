/*
1、遍历两次的做法
（1）先找到整个数组中的最高点
（2）分别处理 左半部分 和 右半部分。
（3）例如 左半部分，从左到右遍历，遍历的过程中记录 left_max_height，如果当前遍历元素小于 当前的 left_max_height 的话，那就可以存储雨水。如果大于的话，那就更新 left_max_height

2、只需遍历一次的做法，想办法使得上述第一种方法不遍历两次
（1）双指针，如果 左边的值 < 右边的值，则移动 左边的。
（2）一个位置能容下的雨水量等于它左右两边柱子最大高度的最小值减去它的高度，所以是要移动小的。所以判断条件是 max_left 和 max_right 的大小关系
*/




class Solution {
    public int trap(int[] height) {
        if(height == null || height.length == 0)    return 0;
        int max_height_index = 0, max_height = 0;
        int result = 0;
        for(int i = 0; i < height.length; i ++){
            if(height[i] > height[max_height_index]){
                max_height_index = i;
                max_height = height[i];
            }
        }
        // 先考虑左半部分
        int left_max_height = height[0];
        for(int i = 1; i < max_height_index; i ++){
            if(height[i] < left_max_height){
                result = result + (left_max_height - height[i]);
            }
            else{
                left_max_height = height[i];
            }
        }
        int right_max_height = height[height.length - 1];
        for(int i = height.length - 2; i > max_height_index; i --){
            if(height[i] < right_max_height){
                result = result + (right_max_height - height[i]);
            }
            else{
                right_max_height = height[i];
            }
        }
        return result;
    }
}


class Solution {
    public int trap(int[] height) {
        if(height == null || height.length == 0)    return 0;
        int result = 0;
        int max_left = 0, max_right = 0;
        int left = 0, right = height.length - 1;
        while(left <= right){
            if(max_left < max_right){
                if(height[left] > max_left){
                    max_left = height[left];
                }
                else{
                    result = result + (max_left - height[left]);
                }
                left ++;
            }
            else{
                if(height[right] > max_right){
                    max_right = height[right];
                }
                else{
                    result = result + (max_right - height[right]);
                }
                right --;
            }
        }
        return result;
    }
}
