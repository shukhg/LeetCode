/*
（1）贪心算法
（2）想清楚，其实是每次遇到 '.'，则 count ++，并且后面 2 个位置就不用考虑了
*/



import java.util.*;
public class test{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i = 0; i < t; i ++){
            int m = sc.nextInt();
            String str = sc.next();
            int count = 0;
            for(int j = 0; j < m; j ++){
                if(str.charAt(j) == '.'){
                    count ++;
                    j = j + 2;
                }
            }
            System.out.println(count);
        }
    }
}