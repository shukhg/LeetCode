/*
二分查找，记住 left = 1, right = x
然后中间是  mid 与 x / mid 判断
*/


class Solution {
    public int mySqrt(int x) {
        if(x == 0)
            return 0;
        if(x <= 3)
            return 1;
        int left = 1 , right = x;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if( mid == x / mid){
                return mid;
            }
            else if(mid > x / mid){
                right = mid - 1 ;
            }
            else{
                left = mid + 1;
            }
        }
        return right;
    }
}