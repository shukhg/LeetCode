/*
（1）对每一行进行遍历，每一行就可以当作 084题 中的情况了
（2）同样，当作 084题 中的情况的时候，数组两端同样当作用 0 填充，所以长度 + 2
*/



class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        int row = matrix.length, col = matrix[0].length;
        int[] height = new int[col + 2];
        int result = 0;
        for(int i = 0; i < row; i ++){
            Stack<Integer> stack = new Stack<>();
            for(int j = 0; j < col + 2; j ++){
                if(j >= 1 && j <= col){
                    if(matrix[i][j - 1] == '1'){
                        height[j] = height[j] + 1;
                    }
                    else{
                        height[j] = 0;
                    }
                }
                while(!stack.isEmpty() && height[stack.peek()] > height[j]){
                    int cur = stack.pop();
                    result = Math.max(result, (j - stack.peek() - 1) * height[cur]);
                }
                stack.push(j);
            }
        }
        return result;
    }
}

