/*
（1）从右上角开始查找
（2）如果当前元素小于 target，而当前元素左边（仅限于当前行）都小于当前元素，故 target 一定出现在下面的行
（3）如果当前元素大于 target，而当前元素下面（仅限于当前列）都大于当前元素，故 target 一定出现在左边的列
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