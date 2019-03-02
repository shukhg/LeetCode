/*
（1）利用第一行和第一列进行存储，不用额外的空间
（2）首先，记录第一行和第一列是否有 0 
（3）然后，在矩阵中（除了第一行和第一列）查找0，如果有 0 就把 0 在第一行和第一列的投影的位置设置为 0
（4）之后，查找第一行和第一列，发现 0 的话，就把相应的行和列全部设置为0
（5）最后，根据第二步的记录，决定是否把第一行和第一列全部设置为 0
*/


public class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Boolean first_row = false;    // 判断第一行是否有为0的元素
        Boolean first_col = false;    // 判断第一列是否有为0的元素
        for (int i = 0; i < m; i++){    // 第一行判断
            if (matrix[i][0] == 0){
                first_col = true;
                break;
            }
        }
        for (int j = 0; j < n; j++){   // 第一列判断
            if (matrix[0][j] == 0){
                first_row = true;
                break;
            }
            
        }
        for (int i = 1; i < m; i++){        //在矩阵中（非第一行第一列）查找 0
            for (int j = 1; j < n; j++){     
                if (matrix[i][j] == 0){     // 把这个点在第一行和第一列的投影设置为0
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < m ; i++){        // 查找第一列是否有 0
            if (matrix[i][0] == 0){          // 如果有的话，就把 0 对应的行设置为 0
                for (int j = 1; j < n; j++){
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 1; j < n ; j++){       // 查找第一行是否有 0 
            if (matrix[0][j] == 0){          // 如果有的话，就把 0 对应的列设置为 0
                for (int i = 1; i < m; i++){
                    matrix[i][j] = 0;
                }
            }
        }
        if (first_row){                        //  如果第一行有 0 就把第一行全设置为 0
            for (int j = 0; j < n; j++){
                matrix[0][j] = 0;
            }
        }
        if (first_col){                    // 如果第一列有 0 就把第一列全设置为 0
            for (int i = 0; i < m; i++){
                matrix[i][0] = 0;
            }
        }
    }
}
