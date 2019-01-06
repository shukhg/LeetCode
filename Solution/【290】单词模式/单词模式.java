/*
先利用 split() 和 toCharArray()方法将输入的两个字符串全部转化为数组。
然后，再利用 HashMap()  其中 value存储的是索引。
如果两个数组的长度不同，直接返回flase
再循环判断，如果每次遍历到的两个数组中的对应部分索引不同，就返回false
*/



class Solution {
    public boolean wordPattern(String pattern, String str) {
        if(pattern == null && str == null)
            return true;
        if( pattern.length() == 0 && str.length() == 0)
            return true;
        char[] arr1 = pattern.toCharArray();    // 用于存储 pattern拆分的字符数组
        String[] arr2 = str.split(" ");      // 用于存储 str 拆分的各个单词字符串 
        if(arr2.length != arr1.length)
            return false;
        HashMap<Character , Integer > hashmap1 = new HashMap<>();   // value 是索引
        HashMap<String , Integer > hashmap2 = new HashMap<>();
        int index1 = -1;                 // 初始状态
        int index2 = -1;
        for(int i = 0 ; i < arr1.length ; i ++){
            if( hashmap1.containsKey(arr1[i]) == false ){
                hashmap1.put(arr1[i] , i);
            }
            else{
                index1 = hashmap1.get(arr1[i]);
            }
            if( hashmap2.containsKey(arr2[i]) == false){
                hashmap2.put(arr2[i] , i);
            }
            else{
                index2 = hashmap2.get(arr2[i]);
            }
            if(index1 != index2)    // 对应位置的索引不同，直接返回 false
                return false;
        }
        return true;
    }
}