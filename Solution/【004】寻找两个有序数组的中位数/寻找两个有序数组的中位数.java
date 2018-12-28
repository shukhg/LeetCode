/*
*  这道题目，他们用的方法不是很明白，所以就暴力解答了
*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int arr[] = new int[nums1.length + nums2.length];
        System.arraycopy(nums1 , 0 , arr , 0 , nums1.length);
        System.arraycopy(nums2 , 0 , arr , nums1.length  , nums2.length);
        Arrays.sort(arr);
        int len = arr.length;
        if(len % 2 == 1){
            return (double)arr[len / 2];
        }
        else{
            return (double)(arr[len / 2] + arr[len / 2   -  1 ]) / 2;
        }
    }
}