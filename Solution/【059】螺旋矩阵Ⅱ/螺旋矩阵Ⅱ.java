/*
关键还是设置 up down left right
*/


class Solution {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int left = 0, right = n - 1;
        int up = 0, down = n - 1;
        int num = 1;
        while(left <= right && up <= down){
            for(int i = left; i <= right; i ++){
                result[up][i] = num;
                num ++;
            }
            up ++;

            for(int i = up; i <= down; i ++){
                result[i][right] = num;
                num ++;
            }
            right --;

            if(up <= down){
                for(int i = right; i >= left; i --){
                    result[down][i] = num;
                    num ++;
                }
                down --;
            }

            if(left <= right){
                for(int i = down; i >= up; i --){
                    result[i][left] = num;
                    num ++;
                }
                left ++;
            }
        }
        return result;
    }
}