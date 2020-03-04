/*
（1）两趟遍历，第一趟遍历将矩阵中的元素标记，第二趟遍历进行赋值
（2）1 —— 保持 1、-1 —— 1 转 0、0 —— 保持 0、-2 —— 0 转 1
（3）第一趟标记：统计周围 8 个位置的数据，为了方便可以先统计一个三行的矩阵（也可能在边界，此时为 2 行）的 1 的个数（-1 也要考虑）
    然后区分中心点是 1 还是 0，分别对应不同的结果
（4）第二趟赋值：对 1 和 -2 进行赋值为 1，对其他赋值为 0 


1——保持1
-1——1转0
0——保持0
-2——0转1
*/



class Solution {
    public void gameOfLife(int[][] board) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = checkLoc(board, i, j);
            }
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = board[i][j] == 1 || board[i][j] == -2 ? 1 : 0;
            }
        }
    }

    public int checkLoc(int[][] board, int i, int j){
        int count = 0;
        int left = Math.max(j - 1, 0);
        int right = Math.min(j + 1, board[i].length - 1);
        int top = Math.max(i - 1, 0);
        int bottom = Math.min(i + 1, board.length - 1);
        for(int x = top; x <= bottom; x++){
            for(int y = left; y <= right; y++){
                count = board[x][y] == 1 || board[x][y] == -1 ? count + 1 : count;
            }
        }
        return board[i][j] == 1 ? (count == 3 || count == 4 ? 1 : -1) : (count == 3 ? -2 : 0);
    }
}