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
（1）首先要理解的是，二维数组转为一维数组的关键点在于用一维数组去模拟二维数组，也就是一维数组能够在 dp 更新的时候能够实现二维数组的功能。
    所以此处是用一维数组表示最下面一层的情况，然后在 dp 数组更新当前层的时候实现下层的逻辑
（2）下层是依赖上层的结果，反过来其实上层也可以当作依赖。而且如果从上层往下层遍历，左右边界要非常小心，所以肯定是从下往上遍历更合适
（3）内层循环一定要从左边往右边遍历，如果是从右边往左边遍历的话，会出现刚刚更新的 dp[j + 1] 被放入了 dp[j] 的计算中
*/


class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[] dp = new int[len];
        for(int i = 0; i < len; i ++){
            dp[i] = triangle.get(len - 1).get(i);
        }
        for(int i = len - 2; i >= 0; i --){
            for(int j = 0; j < triangle.get(i).size(); j ++){
                int sum1 = triangle.get(i).get(j) + dp[j];
                int sum2 = triangle.get(i).get(j) + dp[j + 1];
                dp[j] = Math.min(sum1, sum2);
            }
        }
        return dp[0];
    }
}