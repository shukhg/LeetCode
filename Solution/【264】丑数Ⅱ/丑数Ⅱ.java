/*
通俗易懂的解释：
首先从丑数的定义我们知道，一个丑数的因子只有2,3,5，那么丑数p = 2 ^ x * 3 ^ y * 5 ^ z
换句话说一个丑数一定由另一个丑数乘以2或者乘以3或者乘以5得到，那么我们从1开始乘以2,3,5，就得到2,3,5三个丑数
在从这三个丑数出发乘以2,3,5就得到4，6,10,6，9,15,10,15,25九个丑数
我们发现这种方法会得到重复的丑数，而且我们题目要求第N个丑数，这样的方法得到的丑数也是无序的。那么我们可以维护三个队列：
（1）丑数数组： 1
乘以2的队列：2
乘以3的队列：3
乘以5的队列：5
选择三个队列头最小的数2加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
（2）丑数数组：1,2
乘以2的队列：4
乘以3的队列：3，6
乘以5的队列：5，10
选择三个队列头最小的数3加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
（3）丑数数组：1,2,3
乘以2的队列：4,6
乘以3的队列：6,9
乘以5的队列：5,10,15
选择三个队列头里最小的数4加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
（4）丑数数组：1,2,3,4
乘以2的队列：6，8
乘以3的队列：6,9,12
乘以5的队列：5,10,15,20
选择三个队列头里最小的数5加入丑数数组，同时将该最小的数乘以2,3,5放入三个队列；
（5）丑数数组：1,2,3,4,5
乘以2的队列：6,8,10，
乘以3的队列：6,9,12,15
乘以5的队列：10,15,20,25
*/


// 附加题 丑数的判断
class Solution {
    public int nthUglyNumber(int n) {
        if(n <= 0)
            return 0;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int i2 = 0 , i3 = 0 , i5 = 0;
        while(list.size() < n){
            int m2 = list.get(i2) * 2;
            int m3 = list.get(i3) * 3;
            int m5 = list.get(i5) * 5;
            int min = Math.min(m2 , Math.min(m3 , m5));
            list.add(min);
            if(min == m2)
                i2 ++;
            if(min == m3)
                i3 ++;
            if(min == m5)
                i5 ++;
        }
        return list.get(list.size() - 1);
    }
}


public class Solution {
    public int GetUglyNumber_Solution(int index) {
        if(index <= 0 )
            return 0;
        ArrayList<Integer> list = new ArrayList<>();
        int i2 = 0 , i3 = 0 , i5 = 0;      // 三个"队列"
        list.add(1);
        while(list.size() < index){
            int m2 = list.get(i2) * 2;
            int m3 = list.get(i3) * 3;
            int m5 = list.get(i5) * 5;
            int min = Math.min(m2 , Math.min(m3 , m5));
            list.add(min);
            if(min == m2){
                i2 ++;
            }
            if(min == m3){
                i3 ++;
            }
            if(min == m5){
                i5 ++;
            }
        }
        return list.get(index - 1);
    }
}