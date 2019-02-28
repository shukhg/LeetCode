/*
关键还是设置 up down left right
*/


class Solution {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int up = 0 , down = n - 1 , left = 0 , right = n - 1;
        int val = 1;
        while(up <= down && left <= right){
            for(int i = left ; i <= right ; i ++){
                result[up][i] = val;
                val ++;
            }
            up ++;
            
            for(int i = up ; i <= down ; i ++){
                result[i][right] = val;
                val ++;
            }
            right --;
            
            if(up <= down){
                for(int i = right ; i >= left ; i --){
                    result[down][i] = val;
                    val ++;
                }
                down --;
            }
            
            if(left <= right){
                for(int i = down ; i >= up ; i --){
                    result[i][left] = val;
                    val ++;
                }
                left ++;
            }
        }
        return result;
    }
}