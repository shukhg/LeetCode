/*
（1）不同数组求和，很大概率是用 hashmap 先存储一个的结果，然后再遍历的过程中看到是否存在 key
（2）可以仿照 "两数之和" 的方法，得到 O(n^3) 的解法
（3）其实还可以再进阶一步，可以两个数组合，将结果放入 hashmap，后面遍历查找的时候，也是以两个数组的和进行查找，这样就转化为了 O(n^2) 了
*/



class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int sum = 0;
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < B.length; j++){
                sum = A[i] + B[j];
                if(map.containsKey(sum)){
                    map.put(sum, map.get(sum) + 1);
                }
                else{
                    map.put(sum, 1);
                }
                
            }
        }
        int count = 0;
        int sum2 = 0;
        for(int i = 0; i < C.length; i++){
            for(int j = 0; j < D.length; j++){
                sum2 = -C[i] - D[j];
                if(map.containsKey(sum2)){
                    count = count + map.get(sum2);
                }
            }
        }
        return count; 
    }
}