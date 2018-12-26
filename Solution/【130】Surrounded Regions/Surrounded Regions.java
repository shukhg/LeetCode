class Solution {
    int lenRow ,lenCol ;   // 全局变量，为了给 dfs方法用
    public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0)
            return ;
        lenRow = board.length;
        lenCol = board[0].length ;
        for(int i = 0 ; i < lenCol ; i ++){   // 第一行
            if(board[0][i] == 'O')
                dfs(board  , 0 , i );
        }
        for(int i = 0 ; i < lenCol ; i ++){    // 最后一行 
            if(board[lenRow - 1 ][i] == 'O')
                dfs(board  , lenRow - 1 , i );
        }
        for(int j = 0 ; j < lenRow  ; j ++){   // 第一列 
            if(board[j][0] == 'O')
                dfs(board , j , 0);
        }
        for(int j = 0 ; j < lenRow  ; j ++){   // 最后一列
            if(board[j][lenCol - 1] == 'O')
                dfs(board , j , lenCol - 1);
        }
        for(int i = 0 ; i < lenRow ; i ++){  // 遍历矩阵 
            for(int j = 0 ; j < lenCol ; j ++){
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                if(board[i][j] == 'I')
                    board[i][j] = 'O';   
            }
        }
        return ;
    }
    public void dfs(char[][] board , int row , int col ){ // 开始深搜的行号和列号
        // 最好是利用下面这句进行处理，不要在后面的 dfs之前加 if判断
        if( row < 0 || col < 0||row >= lenRow||col >=lenCol||board[row][col]!='O')
            return;
        board[row][col] = 'I';
        dfs(board , row + 1 , col );
        dfs(board , row  , col + 1 );
        dfs(board , row - 1 , col );
        dfs(board , row  , col - 1 ); 
        return ;  
    } 
}

