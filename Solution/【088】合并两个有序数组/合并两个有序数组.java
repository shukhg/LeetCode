/*
从后面往前面处理就可以了
哪个数组没有插入完，就把剩余的放进去就行
*/


public class Solution{
	public static void merge(int[] nums1 , int m , int[] nums2 , int n) {
        if(nums2 == null || nums2.length == 0)  return;
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while(i >= 0 &&j >= 0){
            if(nums1[i] > nums2[j]){
                nums1[k] = nums1[i];
                i -- ;
                k --;
            }
            else{
                nums1[k] = nums2[j];
                j--;
                k--;
            }
        }
        if(i == -1){
            while(j >= 0 ){
                nums1[k] = nums2[j];
                k--;
                j--;
                
            }
        }
        else{
            while(i >= 0){
                nums1[k] = nums1[i];
                i--;
                k--;
            }
        }
	}
}