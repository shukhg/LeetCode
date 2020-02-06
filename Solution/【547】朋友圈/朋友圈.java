/*
（1）利用 dfs 搜索
（2）此处没必要在主函数中写二维for循环，因为这里的人都是对称的，只需要一个 for 循环遍历每个人就可以了
（3）dfs 中的 for 循环是需要遍历每个人的
*/




class Solution {
    public int findCircleNum(int[][] M) {
         int length = M.length;  
         int count = 0;   
         boolean[] flag = new boolean[length];   //访问标志
         for(int i = 0; i < length; i++){    // 注意这里是不需要遍历二维数组的，只需要遍历每个人就可以了
             if(flag[i] == false){   
                 dfs(i, M, flag);   
                 count ++;   
             }
         }
         return count;
     }
     public void dfs(int i, int[][] M, boolean[] flag){
         flag[i] = true;
         for(int j = 0; j < M[i].length; j ++){
             if(flag[j] == false && M[i][j] == 1){
                 dfs(j, M, flag);
             }
         }
     }
 }
 