/*
先沿着副对角线交换全部元素，再上半部分与下半部分交换
*/


class Solution {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return ;
        int len = matrix.length;
        for(int i = 0 ; i < len ; i ++){
            for(int j = 0 ; j < len - i ; j ++){
                swap(matrix , i , j , len - j - 1 , len - i - 1);
            }
        }
        for(int i = 0 ; i < len / 2 ; i ++){
            for(int j = 0 ; j < len ; j ++){
                swap(matrix , i , j , len - i - 1 , j);
            }
        }
    }
    public void swap(int[][] matrix , int left_row , int left_col , int right_row , int right_col){
        int temp = matrix[left_row][left_col];
        matrix[left_row][left_col] = matrix[right_row][right_col];
        matrix[right_row][right_col] = temp;
    }
}