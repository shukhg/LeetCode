/*
1、  Integer.MAX_VALUE      Integer.MIN_VALUE 
2、  注意整数反转的算法，其实就一个while循环，要理解
3、  用long的因为防止超过int的界限 
*/


public class Solution {
	public int reverse(int x) {
        long temp = 0 ;
        long reNum;
        if(x < 0){            // 负数的处理 
            reNum = -x;
        }
        else  reNum = x;
        while(reNum != 0 ){     // 要理解这个循环
            temp = temp * 10 + reNum % 10;
            reNum = reNum / 10;
        }
        if(x < 0) temp = - temp;    // 负数的处理
        if(temp < Integer.MIN_VALUE || temp > Integer.MAX_VALUE)  return 0;
        return (int)temp;
    }
}