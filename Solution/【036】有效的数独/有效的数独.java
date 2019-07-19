/*
（1）一行行判断
（2）一列列判断
（3）以3*3的矩阵为单位判断。注意循环怎么写
*/


class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashMap<Character , Integer> hashmap = new HashMap<>();
        for(int i = 0 ; i < 9 ; i++ ){
            HashSet<Character> hash = new HashSet<>();
            for(int j = 0 ; j < 9 ; j++){
                if( hash.contains(board[i][j]) == true){
                    return false;
                }
                else{
                    int digit = board[i][j] - '0';
                    if( digit <= 9 && digit >= 0){
                        hash.add(board[i][j]);
                    }
                }
            }
        }
        for(int j = 0 ;j < 9 ; j++){
            HashSet<Character> hash = new HashSet<>();
            for(int i = 0 ; i < 9 ; i++){
                if(hash.contains(board[i][j]) == true){
                    return false;
                }
                else{
                    int digit = board[i][j] - '0';
                    if(digit <= 9 && digit >= 0){
                        hash.add(board[i][j]);
                    }
                }
            }
        }
        for(int i = 0 ; i < 9 ; i = i + 3){
            for(int j = 0 ; j < 9 ; j = j + 3){
                HashSet<Character> hash = new HashSet<>();
                for(int k = i ; k < i + 3 ; k ++){
                    for(int m = j ; m < j + 3 ; m ++){
                        if(hash.contains(board[k][m]) == true){
                            return false;
                        }
                        else{
                            int digit = board[k][m] - '0';
                            if(digit <= 9 && digit >= 0){
                                hash.add(board[k][m]);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}