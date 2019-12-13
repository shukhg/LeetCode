/*
最简单的方法，考虑每一行、每一列、每个 3x3 的小矩阵 即可。利用 set 去存储，如果出现重复的那就 false
既然上述方法要遍历矩阵 3 次，所以就要想办法使得 1 次遍历就得到结果。这意味着，访问数组中某个元素的时候，需要计算它分别在 3 种情况下是否满足要求。
1-9 这 9 个数字，其实用 1 个 int 类型的数 就可以存储，利用位 运算
所以关键是找好   i j 在这三种情况下的索引  核心是这  int boxNum = i / 3 * 3 + j / 3
*/





class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[] rowCnt = new int[9];
        int[] colCnt = new int[9];
        int[] boxCnt = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ('.' == board[i][j]) {
                    continue;
                }
                int num = board[i][j] - 48;
                // 处理行
                if ((rowCnt[i] >> num) % 2 == 1) {
                    return false;
                } else {
                    rowCnt[i] += 1 << num;
                }
                // 处理列
                if ((colCnt[j] >> num) % 2 == 1) {
                    return false;
                } else {
                    colCnt[j] += 1 << num;
                }
                // 处理框
                int boxNum = i / 3 * 3 + j / 3 ;
                if ((boxCnt[boxNum] >> num) % 2 == 1) {
                    return false;
                } else {
                    boxCnt[boxNum] += 1 << num;
                }
            }
        }
        return true;
    }
}







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