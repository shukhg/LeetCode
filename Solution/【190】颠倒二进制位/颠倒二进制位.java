/*
（1）既然题目说了是 32 位的整数，所以直接遍历这32位即可
（2）每次用 cur 记录当前位的值（0 或者 1，从右到左），然后将此值左移 31 - i 位即为此 cur 的最终位置
（3）更新 n，也就是更新 cur 所代表的位
*/

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for(int i = 0; i < 32; i++){
            int cur = n & 1;      //每次都处理二进制的最低位, 这样方便进行&操作 
            result = result + (cur << (31 - i));  //直接把二进制的最低位安排到最终位置上
            n = n >> 1;       // 更新 n, 丢掉左边的一位
        }
        return result;
    }
}