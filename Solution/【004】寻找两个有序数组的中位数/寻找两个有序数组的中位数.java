/*
（1）实现一个寻找两个数组中第 k 小的算法，此处是关键
    要注意这个判断数组 A 和 B 的第 k/2 位置的比较，决定了后续要在哪个数组中寻找结果 
（2）寻找第 mid 点偏左边的点，再寻找第 mid 点偏右边的点，最后将两个点加起来 * 1.0 然后除以2 即可 
*/


class Solution {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int lengthall = length1 + length2;
        int l = (lengthall + 1) / 2;    // 中间点偏左边的位置
        int r = (lengthall + 2) / 2;    // 中间点偏右边的位置
        int mid_Left = getKMin(nums1 , 0 , nums2 , 0 , l);    // 得到中位数偏左的数
        int mid_Right = getKMin(nums1 , 0 , nums2, 0 , r);    // 得到中位数偏右的数
        double result = (mid_Left + mid_Right) * 1.0 / 2;     // 这里一定要 * 1.0 不然会整除了
        return result;
    }
    public static int getKMin(int[] A, int Astart, int[] B, int Bstart, int k){
        if (Astart > A.length - 1){    // 如果在 A中开始的索引超过了A 的长度
            return B[Bstart + k -1];   // 就在 B中找第 K 小的元素
        }
        if (Bstart>B.length-1){       // 如果在 B 中开始的索引超过了B 的长度
            return A[Astart + k -1];   //  就在 A 中找第 K 小的元素
        }
        if (k == 1){                // k == 1的时候比较 A B开始索引的元素
            return Math.min(A[Astart],B[Bstart]);
        }
 
        int Amin = Integer.MAX_VALUE , Bmin = Integer.MAX_VALUE;
        if (Astart + k/2 - 1 < A.length){   // 往右边取 k/2 位置的元素
            Amin = A[Astart + k/2 -1];     //  A 数组在上述位置的值
        }
        if (Bstart + k/2 - 1 < B.length){  // B 数组在上述位置的值
            Bmin = B[Bstart + k/2 -1];
        }
        if(Amin < Bmin)      // 如果在上述位置，A 的值小于B 的值，则说明要找的第 k 小的点在B 这边
            return getKMin(A , Astart + k/2 , B , Bstart , k - k/2);
        else                 // 否则说明在 A 这边
            return getKMin(A , Astart , B , Bstart + k/2 , k - k/2);
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