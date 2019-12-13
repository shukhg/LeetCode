/*
最简单的方法，考虑每一行、每一列、每个 3x3 的小矩阵 即可。利用 set 去存储，如果出现重复的那就 false
既然上述方法要遍历矩阵 3 次，所以就要想办法使得 1 次遍历就得到结果。这意味着，访问数组中某个元素的时候，需要计算它分别在 3 种情况下是否满足要求。
1-9 这 9 个数字，其实用 1 个 int 类型的数 就可以存储，利用位 运算
所以关键是找好   i j 在这三种情况下的索引  核心是这  int boxNum = i / 3 * 3 + j / 3
*/



class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
        vector<int> row_judge(9, 0);
        vector<int> col_judge(9, 0);
        vector<int> box_judge(9, 0);
        for(int i = 0; i < board.size(); i ++){
            for(int j = 0; j < board.size(); j ++){
                if(board[i][j] == '.'){
                    continue;
                }
                int num = board[i][j] - '0';
                if((row_judge[i] >> num) % 2 == 1)  return false;
                else    row_judge[i] = row_judge[i] + (1 << num);

                if((col_judge[j] >> num) % 2 == 1)  return false;
                else    col_judge[j] = col_judge[j] + (1 << num);

                if((box_judge[i / 3 * 3 + j / 3] >> num) % 2 == 1)  return false;
                else    box_judge[i / 3 * 3 + j / 3] = box_judge[i / 3 * 3 + j / 3] + (1 << num);
            }
        }
        return true;
    }
};





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