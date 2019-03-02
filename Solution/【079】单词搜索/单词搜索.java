/*
和剑指offer 中的题目差不多
*/


class Solution {
    public  boolean exist(char[][] board, String word) {
        if(board == null || word == null || board.length == 0 || word.length() == 0)
            return false;
        boolean[][] judge = new boolean[board.length][board[0].length];
        for(int i = 0 ; i < board.length ; i ++){
            for(int j = 0 ; j < board[0].length ; j ++){
                if(board[i][j] == word.charAt(0) ){
                    judge[i][j] = true;
                    dfs( board , word , i , j , 0 , judge ); // 从 [i][j] 开始dfs
                    judge[i][j] = false;
                    
                }
                if(result == true)
                    return true;
            }
        }
        return false ;  
    }
    boolean result = false;
    public   void dfs( char[][] board , String word , int row , int col , int count  , boolean[][] judge ){  // count 存储字符串中的第几个字符
        //if( board[row][col] != word.charAt(count) )     把这个提到递归调用外面了，不然遇到特殊情况会超时
            //return ;
        if( count == word.length() - 1){
            result = true;
            return ;
        }
        if( result == true )         // 必须要有这个，要是没有这个会多很多计算
            return ;
        if( col != 0 && judge[row][col - 1] ==  false && board[row][col - 1] == word.charAt(count + 1 ) ){
            judge[row][col - 1] = true;
            dfs( board , word , row , col - 1 , count + 1 , judge );
            judge[row][col - 1] = false;
        }
        if( col != board[0].length - 1 && judge[row][col + 1] == false  && board[row][col + 1] == word.charAt(count + 1 ) ){
            judge[row][col + 1] = true;
            dfs( board , word , row , col + 1 , count + 1 , judge );
            judge[row][col + 1] = false;
        }
        if( row != 0 && judge[row - 1][ col ] == false && board[row - 1][col] == word.charAt(count + 1)  ){
            judge[ row - 1][col] = true;
            dfs( board , word , row - 1 , col , count + 1 , judge );
            judge[row - 1][col] = false; 
        }
        if( row != board.length - 1 && judge[row + 1] [ col] == false  && board[row + 1][col] == word.charAt(count + 1) ){
            judge[row + 1][col] = true;
            dfs(board , word , row + 1 , col , count + 1 , judge );
            judge[row + 1][col] = false;
        } 
    }
}