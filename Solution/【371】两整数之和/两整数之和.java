/*
1、计算ab的进位的话，只有二者同为1才进位，因此进位可以标示为 (a and b) << 1 ，注意因为是进位，所以需要向左移动1位 
2、于是a+b可以看成 （a xor b）+ （(a and b) << 1），这时候如果 (a and b) << 1 不为0
   递归调用这个方式，因为（a xor b）+ （(a and b) << 1） 也有可能进位，所以我们需要不断的处理进位。
*/


class Solution {
    public int getSum(int a, int b) {
        if(a == 0 )
            return b;
        if(b == 0)
            return a;
        int temp1 = a ^ b;  
        int temp2 = (a & b )<< 1;   // temp2 是体现了进位
        if(temp2 != 0){
            return getSum( temp1 , temp2);
        }
        else{
            return temp1;
        }
    }
}