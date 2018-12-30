/*
初始化一个 flag = 1，也就是只有最右边1位是1，然后利用 flag与 n 进行 & ，再将 flag = flag << 1 左移重复此操作

Java中右移规则：
    带符号右移，正数右移高位补0，负数右移高位补1
      >>> 无符号右移 ， 全部以0填充
*/

// 感觉这个更好理解一点 
public class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        int flag = 1;
        while( flag != 0 ){
            if( (n & flag ) != 0){     // n 与 flag中为 1 的位进行 与 操作 
                count ++;
            }
            flag = flag << 1;   // flag中只有1位为1，这位逐渐向左移动
        }
        return count ;
    }
}



public class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0 ){
            if( (n & 1) == 1){  // 真正有影响的是最右边1位 
                count ++;
            }
            n = n >>> 1;  
        }
        return count ;
    }
}