/*
（1）输入 直接用 nextInt()，可以用 for 循环连续读取多个 nextInt()
（2）利用 TreeMap，将 map 中的 key 按照从小到大排序（默认是从小到大）
（3）将 难度 和 收益 利用 map 存起来
（4）对于 M 个能力值，也将其加入到 map 中，不过如果 没有对应的收益的话，就默认加入 0
（5）重新遍历 map 的 key-value 对（利用 Map.Entry<A, B> 和 map.entrySet() 实现）
    因为 key 是从小到大，所以可以拿到遍历过程中的 value 最大值
*/



import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] ability = new int[m];   // 存放能力值
        //利用TreeMap，因为它里面的数据是默认按照key升序排列，方便比较
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < n; i ++){   // 存入难度和薪酬
            int hard = sc.nextInt();
            int money = sc.nextInt();
            map.put(hard, money);
        }
        for(int i = 0; i < m; i ++){
            int t = sc.nextInt();
            ability[i] = t;
            //不在工作难度边界处的，放入map，且对应薪酬为0
            //若能力和工作难度相等，则不存，因为已经有了这组数据，下次比较的时候可以直接用
            if(!map.containsKey(t)){
                map.put(t,0);
            }
        }
        int max = Integer.MIN_VALUE;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){   // key 是从小到大
            max = Math.max(max, entry.getValue());
            map.put(entry.getKey(), max);
        }
        for(int i = 0; i < m ; i ++){
            System.out.println(map.get(ability[i]));
        }
    }
}