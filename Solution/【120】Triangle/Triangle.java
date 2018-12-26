/*
此题肯定是利用动态规划，很容易想到用二维数组，从上到下进行遍历并且更新二维数组
中间的过程注意要区分最左边结点、最右边结点和中间结点
*/



class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.get(0).size() == 0 )
            return 0;
        int[][] dp = new int[triangle.size()][triangle.size()];
        dp[0][0] = triangle.get(0).get(0);        // dp数组的第一行（只有一个元素）
        for(int i = 1 ; i < dp.length ; i ++){
            for(int j = 0 ; j < dp.length ; j ++){    // j 的范围是 0 到 i
                if( j == 0 ){                // 此行的最左边
                    dp[i][0] = dp[i - 1][0] + triangle.get(i).get(j);
                }
                else if( j == i ){          // 注意是从上层的 j - 1 传递到此层的 j 
                    dp[i][j] = dp[ i - 1][j - 1] + triangle.get(i).get(j);        // 此行的最右边
                }
                else if( j < i ){        // 此行的中间 
                    dp[i][j] = Math.min( dp[ i - 1][j] , dp[i - 1][j - 1]  ) + triangle.get(i).get(j);
                }
            }
        }
        int min = dp[dp.length - 1][0];
        for(int i = 1 ; i < dp.length ; i ++){
            if(dp[dp.length - 1][i] < min)
                min = dp[dp.length - 1][i];
        }
        return min ;
    }
}

/*
更好的解法同样是利用动态规划，但是不再使用二维数组，而只使用一个一维数组
从后往前（从下往上）进行遍历，同时更新一维 dp数组
注意更新的时候是从下往上，所以 dp 中 j 部分的索引必须体现下层的 j的索引
也就是有了  dp[j]  dp[j + 1] 
还要注意内层循环的遍历范围，一定要是 triangle.get(i).size()
而不能是全部的 length
*/


class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.size();
        if(triangle == null || triangle.get(0).size() == 0)
            return 0;
        if(length == 1 )
            return triangle.get(0).get(0);
        int[] dp = new int[length];
        for(int i = 0 ; i < length ; i ++){
            dp[i] = triangle.get(length - 1).get(i);
        }
        for(int i = length - 2 ; i >= 0 ; i --){    // 由后往前更新 dp 
            for(int j = 0 ; j < triangle.get(i).size()  ; j ++){    // 此步是关键，一定要是  triangle.get(i).size()
                int sum1 = triangle.get(i).get(j) + dp[j];      
                int sum2 = triangle.get(i).get(j) + dp[j + 1];    // 此处 j + 1 是下层的体现
                dp[j] = Math.min(sum1 , sum2);
            }
        }
        return dp[0];
    }
}