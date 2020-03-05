/*
（1）一维的情况下是记录前缀和，二维的情况下就记录 左上角的矩阵的和
（2）先用 dp 把整个数组的左上角矩阵和都计算好
（3）因为是左上角，所以 dp数组的长度为 len + 1
（4）设四个位置分别为 a,b,c,d, o是左上角位置，画个图就知道 sum(abcd) = sum(od) − sum(ob) − sum(oc) + sum(oa)
*/


class NumMatrix {
    int[][] dp;   // 记录当前元素左上角矩阵的元素和
    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return ;
        }
        dp = new int[matrix.length + 1][matrix[0].length + 1];   // 因为 dp 是记录左上角，所以此处要 +1
        for(int i = 0; i < matrix.length; i ++){
            for(int j = 0; j < matrix[0].length; j ++){
                dp[i + 1][j + 1] = dp[i + 1][j] + dp[i][j + 1] + matrix[i][j] - dp[i][j];  // 先用 dp 把整个矩阵记录完
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];   // 画个图就可以理解了
    }
}