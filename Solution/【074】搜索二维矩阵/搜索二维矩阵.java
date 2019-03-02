/*
从右上角开始
如果 target大于对应元素，则在右上角元素的下方重新寻找，更新右上角元素
如果 target小于对应元素，则在右上角元素的左方重新寻找，更新右上角元素
*/


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 )
            return false;
        int row = 0;
        int col = matrix[0].length  - 1;
        while( row < matrix.length && col >= 0){
            if(matrix[row][col] == target)
                return true;
            else if( matrix[row][col] < target){
                row ++ ;
            }
            else
                col --;
        }
        return false;
    }
}