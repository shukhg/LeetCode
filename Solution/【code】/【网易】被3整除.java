/*
（1）找规律即可
（2）这样遍历可能会超时，所以其实可以直接根据 left 和 right 的值，判断有多少种情况
*/



import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long left = sc.nextInt();
        long right = sc.nextInt();
        int count = 0;
        for(long i = left; i <= right; i ++){
            if(i % 3 == 2 || i % 3 == 0){
                count ++;
            }
        }
        System.out.println(count);
    }
}