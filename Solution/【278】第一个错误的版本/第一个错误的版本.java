/*
（1）二分查找
（2）需要找的是第一个错误的版本，所以大前提是 找错误的
（3）如果错误，则可能这个就是结果，故 right = mid
（4）如果没错，则这个肯定不可能是结果
（5）因为有 right = mid 的存在，最终 left、right 重合的点就是结果
*/



public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        if(n == 1)  return 1;
        int left = 1, right = n;
        int mid;
        while(left < right){
            mid = left + (right - left) / 2;
            if(isBadVersion(mid)){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }
        return left;
    }
}