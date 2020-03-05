/*
（1）此题和 丑数 的方法几乎一样
（2）把对 2、3、5 的操作换成一个 for 循环即可 
*/




class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if(n < 0)   return 0;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int[] index = new int[primes.length];
        int[] m = new int[primes.length];
        int min;
        while(list.size() < n){
            min = Integer.MAX_VALUE;
            for(int i = 0; i < primes.length; i ++){
                m[i] = list.get(index[i]) * primes[i];
                min = Math.min(min, m[i]);
            }
            list.add(min);
            for(int i = 0; i < primes.length; i ++){
                if(min == m[i]){
                    index[i] = index[i] + 1;
                }
            }
        }
        return list.get(list.size() - 1);
    }
}