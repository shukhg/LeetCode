/*
（1）若一个数是快乐数，最终变换会回到1，因此确定循环终结条件
（2）若不是快乐数，会进入死循环，如何终至死循环，将每次变换过后的值存入 HashSet 中，判断是否出现过重复值，出现则 return false
*/



class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int m = 0;
        while(true){
            while(n != 0){
                m = m + Math.pow(n % 10, 2);
                n = n / s10;
            }
            if(m == 1){
                return true;
            }
            if(set.contains(m)){
                return false;
            }
            else{
                set.add(m);
                n = m;
                m = 0;
            }
        }        
    }
}