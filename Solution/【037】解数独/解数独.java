/*
还是常规的回溯的思路，这里用 3 个矩阵分别表示 3种条件下的数字是否被使用。
跳出递归的边界条件要注意，是走到了右下角的时候跳出来。
对于每个位置，都尝试用 1-9 中可以使用的数字去填充
*/



class Solution {
    public void solveSudoku(char[][] board) {
        boolean[][] row_uesd = new boolean[9][10];   // 记录是否已经出现
        boolean[][] col_used = new boolean[9][10];
        boolean[][][] box_used = new boolean[3][3][10];
        for(int row = 0; row < board.length; row ++){
            for(int col = 0; col < board.length; col ++){
                if(board[row][col] != '.'){
                    int num = board[row][col] - '0';
                    row_uesd[row][num] = true;
                    col_used[col][num] = true;
                    box_used[row / 3][col / 3][num] = true;
                }
            }
        }
        dfs(board, row_uesd, col_used, box_used, 0, 0);
    }
    public boolean dfs(char[][] board, boolean[][] row_used, boolean[][] col_used, boolean[][][] box_used, int row, int col){
        if(col == board[0].length){   // 边界情况，判断是否跳出循环
            col = 0;
            row ++;          
            if(row == board.length) return true;
        }
        if(board[row][col] == '.'){
            for(int num = 1; num <= 9; num ++){
                boolean can_used = !(row_used[row][num] || col_used[col][num] || box_used[row/3][col/3][num]);
                if(can_used){
                    row_used[row][num] = true;
                    col_used[col][num] = true;
                    box_used[row/3][col/3][num] = true;
                    board[row][col] = (char)('0' + num);
                    if(dfs(board, row_uesd, col_used, box_used, row, col + 1))  return true;
                    board[row][col] = '.';
                    row_used[row][num] = false;
                    col_used[col][num] = false;
                    box_used[row/3][col/3][num] = false;
                }
            }
        }
        else{
            return dfs(board, row_used, col_used, box_used, row, col + 1);
        }
        return false;
    }
}