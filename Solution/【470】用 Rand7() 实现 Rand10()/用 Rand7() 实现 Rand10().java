/*
（1）将 1-7 这7个数字分为两份，一份是 1-3，一份是 5-7，遇到 4 则丢弃；假如是第一份，然后再生成 1-5 的数；如果是第二份则 1-5 的数的基础上 +5
（2）利用两个 rand7 可以实现一个 1-49 的随机数（类似于矩阵），然后 40-49 % 10 的余数 +1 就是 1-10 的随机数了 
*/



class Solution extends SolBase {
    public int rand10() {
        int row = rand7();
        int col = rand7();
        int index = col + (row - 1) * 7;  // 生成 1-49 之间的随机数
        while(index < 40){
            row = rand7();
            col = rand7();
            index = col + (row - 1) * 7;
        }
        return (index % 10) + 1;
    }
}



class Solution extends SolBase {
    public int rand10() {
        int choose = 0;
        int temp = rand7();
        while(true){
            if(temp <= 3){
                choose = 1;
                break;
            }
            else if(temp >= 5){
                choose = 2;
                break;
            }
            else{
                temp = rand7();
            }
        }
        while(true){
            temp = rand7();
            if(temp <= 5){
                break;
            }
        }
        if(choose == 1){
            return temp;
        }
        else{
            return temp + 5;
        }
    }
}


