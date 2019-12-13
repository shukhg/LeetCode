/*
利用 matrix[0][0] 作为第一行的标记，再设置一个 col_flag 作为第一列的标记
*/


class Solution {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        boolean col_flag = false;

        for(int i = 0; i < rows; i ++){
            if(matrix[i][0] == 0){   // 第一列的记录，这里考虑了 m[0][0] 位置
                col_flag = true;
            }
            for(int j = 1; j < cols; j ++){  // 其他列的记录，这里的 i 是可以取到 0 的，所以可以用 m[0][0] 位置来表示第一行
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for(int i = 1; i < rows; i ++){
            for(int j = 1; j < cols; j ++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        if(matrix[0][0] == 0){
            for(int j = 0; j < cols; j ++){
                matrix[0][j] = 0;
            }
        }

        if(col_flag){
            for(int i = 0; i < rows; i ++){
                matrix[i][0] = 0;
            }
        }
    }
}
