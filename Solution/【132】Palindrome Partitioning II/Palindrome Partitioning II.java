/*
后面还有个方法2，有时候会超出内存限制。
*/


 /*
 
 dp二维数组dp[i][j]表示字符串[i,j]从第i个位置（包含）到第j个位置（包含） 是否是回文。

 如何判断字符串[i,j]是不是回文？

 1. dp[i+1][j-1]是回文且 s.charAt(i) == s.charAt(j)。

 2. i==j（i，j是用一个字符）

 3. j=i+1（i，j相邻）且s.charAt(i) == s.charAt(j)

 当字符串[i,j]是回文后，说明从第i个位置到字符串第len位置的最小count数可以被更新了，

 即，Math.min(count[i], count[j+1] + 1) 

 最后返回count[0] - 1。把多余加的那个对于第len位置的切割去掉，即为最终结果。
 */


 
public class Solution {
    public int minCut(String s) {
        int[][] dp=new int[s.length()][s.length()];   // 表示两个元素之间形成的子串是否是回文串 
        int[] count=new int[s.length() + 1];    // 表示从位置i开始到最后的最小划分个数，包含了 len下标的位置
        
        for(int i = s.length() - 1 ; i >= 0 ; i --)  // 从后往前走
        {
            count[i] = Integer.MAX_VALUE;
            for(int j = i ; j < s.length() ; j ++)    // 因为 dp[i + 1][j - 1] == 1 限制了 i j 遍历的顺序 
            {
                if(s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1] == 1))  // 注意此处是否是回文串的判断 
                {
                	dp[i][j] = 1;
                	count[i] = Math.min(1 + count[j + 1] , count[i]);  // 状态转移方程
                }
            }
        }
        return count[0] - 1;       // 因为是倒着走的，把多余加的那个对于第len位置的切割去掉，即为最终结果
    }
}





class Solution {
    public int minCut(String s) {
        if(s == null || s.length() == 0)
            return 0;
        int[] dp = new int[s.length()]; //dp[i]存放(0,i)即以i的字符结束的子串的最小切割数
        dp[0] = 0;                      // 一个字符不需要切割
        for(int i = 1 ; i < s.length() ; i ++){
            if( judge(s.substring(0 , i + 1 )) == true )  //下标 0 到 i 的子串为回文串
                dp[i] = 0;                               // 下标 0 到 i 不用切割 
            else
                dp[i] = i  ;                             // 最多需要切割 i  次
            for(int j = i ; j >= 1 ; j --){                // 再从 i 开始从后往前遍历，j = 0就没意义了
                if(judge( s.substring( j , i + 1 )) == true )   // 下标 j 到 i 为回文串
                    dp[i] = Math.min(dp[i] , dp[j - 1 ] + 1 );   // 更新 dp[i]
            }
        }
        return dp[s.length() - 1];
    }
    public boolean judge(String s ){
        if(s == null || s.length() <= 1)
            return true;
        for(int i = 0 ; i < s.length() / 2 ; i ++){
            if(s.charAt(i) != s.charAt(s.length() - 1 - i))
                return false;
        }
        return true;
    }
}