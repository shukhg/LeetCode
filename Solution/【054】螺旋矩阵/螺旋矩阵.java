/*
（1）设置 up down left right 四个指针，分别表示在上 下 左 右 四个部分移动的情况。每个指针移动一次，就要 -1
（2）注意好边界条件，比如 先 上 后 右 ，再执行 下 的时候，可能会导致一行遍历2次，所以要进行判断。
*/



class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return result;
        }
        int left = 0, right = matrix[0].length - 1;
        int up = 0, down = matrix.length - 1;
        while(left <= right && up <= down){
            for(int i = left; i <= right; i ++){
                result.add(matrix[up][i]);
            }
            up ++;

            for(int i = up; i <= down; i ++){
                result.add(matrix[i][right]);
            }
            right --;

            if(up <= down){
                for(int i = right; i >= left; i --){
                    result.add(matrix[down][i]);
                }
                down --;
            }
            if(left <= right){
                for(int i = down; i >= up; i --){
                    result.add(matrix[i][left]);
                }
                left ++;
            }
        }
        return result;
    }
}
