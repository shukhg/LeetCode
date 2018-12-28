/*
对比于求两个数相加的最大和，此处也是一样的原理。
设置左右两个指针，两个指针分别往中间移动。
但是要理解的是，为什么是数组值小的那个指针往中间移动
因为如果是大的移动，就算是往中间移动的过程中遇到的数再大，也是由更小的元素限定了能装多少水。
*/


class Solution {
    public int maxArea(int[] height) {
        if(height.length==2)return Math.min(height[0], height[1]);
        int maxarea = 0;
        int left=0,right=height.length-1;
        while(left<height.length-1 && right>=0 && right>left){
            maxarea = Math.max(maxarea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] > height[right])   // 哪个指针的值更小，就移动哪个指针
            {
                right--;
            }
            else
            {
                left++;
            }
        }
        return maxarea;
    }
}