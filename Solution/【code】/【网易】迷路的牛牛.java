/*
（1）利用数组，代表 北-东-南-西 四个方向
（2）向左、向右 其实就是 index 指针 - 1 或者 + 1
（3）最后对 index 指针取余数，但是可能为负数，注意负数的处理
*/





import java.util.*;
public class test{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();
        char[] dict = new char[]{'N', 'E', 'S', 'W'};
        int index = 0;
        for(int i = 0; i < n; i ++){
            if(str.charAt(i) == 'R'){
                index ++;
            }
            else{
                index --;
            }
        }
        index = index % 4;
        if(index < 0){
            index = index + 4;
        }
        System.out.println(dict[index]);
    }
}

