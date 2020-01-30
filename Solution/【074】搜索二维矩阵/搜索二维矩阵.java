/*
二分查找，把二维数组看作一维数组，注意一维的索引和二维索引的变化关系
*/


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)   return false;
        int rows = matrix.length, cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;   // 右下角的位置
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(matrix[mid / cols][mid % cols] == target){   // mid 在二维数组中的点
                return true;
            }
            else if(matrix[mid / cols][mid % cols] < target){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        return false;
    }
}