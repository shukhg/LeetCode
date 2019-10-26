/*
https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/    这个解答很好，仔细看看图并且理解
核心思想是 两个排序数组 求其 topk 的方法。这个方法说不定以后也会用，一定要掌握

*/

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;  
    }
    
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1 
        if (len1 > len2) 
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) 
            return nums2[start2 + k - 1];

        if (k == 1) 
            return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
}




// 暴力解法
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



// 申请一个新数组，然后将两个有序数组进行合并
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length_1 = nums1.length;
        int length_2 = nums2.length;
        int[] nums = new int[length_1 + length_2];
        if(length_1 == 0){
            int mid = length_2 / 2;
            if(length_2 % 2 == 0){
                return (nums2[mid] + nums2[mid - 1]) / 2.0;
            }
            else{
                return nums2[mid];
            }
        }
        if(length_2 == 0){
            int mid = length_1 / 2 ;
            if(length_1 % 2 == 0){
                return (nums1[mid] + nums1[mid - 1]) / 2.0;
            }
            else{
                return nums1[mid];
            }
        }
        int count = 0;
        int i = 0, j = 0;
        while(count < (length_1 + length_2)){
            if(i == length_1){
                while(j != length_2){
                    nums[count] = nums2[j];
                    count ++;
                    j ++;
                }
                break;
            }
            if(j == length_2){
                while(i != length_1){
                    nums[count] = nums1[i];
                    count ++;
                    i ++;
                }
                break;
            }
            if(nums1[i] < nums2[j]){
                nums[count] = nums1[i];
                count ++;
                i ++;
            }
            else{
                nums[count] = nums2[j];
                count ++;
                j ++;
            }
        }
        if (count % 2 == 0) {
            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
        } 
        else {
            return nums[count / 2];
        }
    }
}





