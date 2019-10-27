


class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int len1 = nums1.size();
        int len2 = nums2.size();
        
        if((len1 + len2) % 2 == 0){
            int left = getKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, (len1 + len2) / 2);
            int right = getKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, (len1 + len2) / 2 + 1);
            return (left + right) / 2.0;
        }
        else{
            return getKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, (len1 + len2) / 2 + 1);
        }
    }
    double getKth(vector<int>& nums1, int start1, int end1, vector<int>& nums2, int start2, int end2, int k){
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        
        if(len1 > len2){
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        }
        if(len1 == 0){
            return nums2[start2 + k - 1];
        }
        if(k == 1){
            return min(nums1[start1], nums2[start2]);
        }
        int i = start1 + min(k/2, len1) - 1;
        int j = start2 + min(k/2, len2) - 1;
        
        if(nums1[i] > nums2[j]){
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else{
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
};