/*
（1）先用 case 画个图，从右上角出发。
（2）如果当前元素 < 0，则此列下面的元素全部小于 0，col --
（3）如果当前元素 >= 0，则 row ++
*/



class Solution {
    public int countNegatives(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0){
            return 0;
        }
        int row = 0, col = grid[0].length - 1;
        int count = 0;
        while(row < grid.length && col >= 0){
            if(grid[row][col] < 0){
                count = count + grid.length - row;
                col --;
            }
            else if(grid[row][col] >= 0){
                row ++;
            }
        }
        return count;
    }
}