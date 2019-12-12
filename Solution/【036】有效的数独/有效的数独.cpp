




class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
        if(board.empty() || board[0].empty() || board.size() == 0 || board[0].size() == 0)  return false;
        for(int i = 0 ; i < 9 ; i++ ){
            set<char> my_set;
            for(int j = 0 ; j < 9 ; j++){
                if( my_set.find(board[i][j]) != my_set.end()){
                    return false;
                }
                else{
                    int digit = board[i][j] - '0';
                    if( digit <= 9 && digit >= 0){
                        my_set.insert(board[i][j]);
                    }
                }
            }
        }
        for(int j = 0 ;j < 9 ; j++){
            set<char> my_set;
            for(int i = 0 ; i < 9 ; i++){
                if(my_set.find(board[i][j]) != my_set.end()){
                    return false;
                }
                else{
                    int digit = board[i][j] - '0';
                    if(digit <= 9 && digit >= 0){
                        my_set.insert(board[i][j]);
                    }
                }
            }
        }
        for(int i = 0; i < board.size(); i = i + 3){
            for(int j = 0; j < board[0].size(); j = j + 3){
                set<char> my_set;
                for(int k = i; k < i + 3; k ++){
                    for(int m = j; m < j + 3; m ++){
                        if(my_set.find(board[k][m]) != my_set.end()){
                            return false;
                        }
                        else{
                            int digit = board[k][m] - '0';
                            if(digit <=9 && digit >=0){
                                my_set.insert(board[k][m]);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
};